package com.themangoproject.ui;

import com.themangoproject.db.h2.DBMovie;
import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.model.Actor;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.Person;
import com.themangoproject.model.PersonExistsException;
import com.themangoproject.model.Role;
import com.themangoproject.ui.model.ActorComboBoxModel;
import com.themangoproject.ui.model.PersonComboBoxModel;
import com.themangoproject.ui.model.RoleComboBoxModel;
import com.themangoproject.webservice.AmazonMovieASINQuery;
import com.themangoproject.webservice.AmazonMovieASINQuery.AmazonASINResult;

import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MovieAddEditDialog is a dialog to add and edit movies. Movie
 * details can be added along with owner and borrower infomation, and
 * a thumbnail image. Some of the entry of movie information can be
 * simplified if you have an Amazon ASIN number for the movie. It will
 * retrieve basic movie information and a thumbnail image.
 * 
 * @author Kyle Ronning, Zach Varberg
 * 
 */
public class MovieAddEditDialog extends javax.swing.JDialog {
    /** Generated serial UID */
    private static final long serialVersionUID = 5583966775480012466L;

    /** A Movie for the dialog to work with. */
    private Movie m;
    
    private SwingWorker<String, Object> amazonWorker;
    
    private AmazonQueryFrame amazonQueryFrame;

    /**
     * Creates a new dialog MovieAddEditDialog to add or edit a movie.
     * 
     * @param parent
     *            The parent frame.
     * @param set
     *            the dialog modal if true.
     */
    public MovieAddEditDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setCombBoxModels();
        amazonQueryFrame = new AmazonQueryFrame(this, true);
        amazonQueryFrame.addASINChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                asinTF.setText((String)e.getSource());
                amazonWorker.execute();
            }
        });
        
        m = null;
        Image im = new ImageIcon(
                getClass()
                        .getResource(
                                "/com/themangoproject/ui/images/defaultMangoLogo.jpg"))
                .getImage();
        this.jLabel15.setIcon(new ImageIcon(im.getScaledInstance(160,
                160, Image.SCALE_DEFAULT)));
        
        
        amazonWorker = new SwingWorker<String, Object>() {
        	
        	private String oldQueryButtonText;
            
        	@Override
        	protected void done() {
        		amazonRetrieveButton.setEnabled(true);
        		amazonRetrieveButton.setText(oldQueryButtonText);
        	}
        	
			@Override
			protected String doInBackground() throws Exception {
				this.oldQueryButtonText = amazonRetrieveButton.getText();
				amazonRetrieveButton.setEnabled(false);
				amazonRetrieveButton.setText("Retrieving Data...");
	            AmazonMovieASINQuery amazon = new AmazonMovieASINQuery();
	            AmazonASINResult asinResult = amazon.getMovieWithASIN(asinTF.getText());
	            if (asinResult != null) {
	                titleTF.setText(asinResult.getTitle());
	                directorTF.setText(asinResult.getDirector());
	                yearTF.setText(asinResult.getReleaseDate());
	                ratingCB.setSelectedItem(asinResult.getRating());
	                try {
	                    runtimeSpinner.setValue(Integer
	                            .parseInt(asinResult.getRuntime()));
	                } catch (NumberFormatException e) {
	                    runtimeSpinner.setValue(0);
	                }
	                jLabel15.setIcon(asinResult.getMovieImage());
	                // Set as DVD if from amazon. There is no way to know
	                // the format easily.
	                typeCB.setSelectedItem("DVD");
	                // actors
	                List<String> actors = asinResult.getActors();
	                for (String actor : actors) {
	                    String[] actorName = actor.split(" ");
	                    if (actorName.length == 2)
	                        MangoController.getInstance().addActor(
	                                actorName[0], actorName[1]);
	                    else if (actorName.length == 3)
	                        MangoController.getInstance().addActor(
	                                actorName[0] + " " + actorName[1],
	                                actorName[2]);
	                    else
	                        MangoController.getInstance().addActor(actor,
	                                "");
	                    addSubstractActorsPanel
	                            .createAndSetSelected(actor, "", "");
	                }

	            } else {
	                JOptionPane
	                        .showMessageDialog(
	                                MovieAddEditDialog.this,
	                                "Amazon was unable to gather requested information.\n"
	                                        + "Check your ASIN number and your Internet connection",
	                                "Could not gather information",
	                                JOptionPane.INFORMATION_MESSAGE);
	            }
	            return "Done";
			}
        };
    }

    /**
     * Populates a the dialog with movie information.
     * 
     * @param m
     *            The <code>Movie</code> to provide the movie
     *            information to the dialog.
     */
    public void populateData(Movie m) {
        this.titleTF.setText(m.getTitle());
        this.directorTF.setText(m.getDirector());
        if (m.getYear() == -1)
            this.yearTF.setText("");
        else
            this.yearTF.setText(new Integer(m.getYear()).toString());
        this.ratingCB.setSelectedItem(m.getRating());
        this.runtimeSpinner.setValue(m.getRuntime());
        if (m.getPurchaseDate() != null) {
            String arr[] = m.getPurchaseDate().toString().split("-");
            this.purchaseDateTF.setText(arr[1] + "/" + arr[2] + "/"
                    + arr[0]);
        }
        this.mangoRatingCB.setSelectedItem("" + m.getMangoRating());
        this.typeCB.setSelectedItem(m.getType());
        this.conditionCB.setText(m.getCondition());
        this.genreTF.setText(genreText(m));
        this.customDescriptionTA.setText(m.getCustomDescription());
        this.asinTF.setText(m.getASIN());

        // Actor
        List<Actor> actors = MangoController.getInstance()
                .getActorsForMovie(m);
        for (Actor actor : actors) {
            List<Role> roles = actor.getRoles();
//            System.out.println(actor.getFirstName() + " " + 
//                    roles.get(0).getCharacter());
            String roleString = "";
            String characterString = "";
            for (Role role : roles) {
//                System.out.println(role.getMovie().getTitle() + " : " + ((DBMovie)role.getMovie()).getId());
//                System.out.println(m.getTitle() + " : " + ((DBMovie)m).getId());
//                System.out.println();
                if (((DBMovie) role.getMovie()).getId() == ((DBMovie) m)
                        .getId()) {
//                    System.out.println(role.getActor().toString());
                    roleString = role.getRole();
//                    System.out.println(roleString);
                    characterString = role.getCharacter();
//                    System.out.println(roleString + "\n");
                }
            }
            this.addSubstractActorsPanel.createAndSetSelected(actor
                    .toString(), roleString, characterString);
        }

        // Set borrower and owner
        if (m.getOwner() != null)
            this.ownerCB.setSelectedItem(m.getOwner().getName());
        if (m.getBorrower() != null) {
            this.borrowerCB
                    .setSelectedItem(m.getBorrower().getName());
            this.borrowedCheckBox.setSelected(true);
        } else
            this.borrowedCheckBox.setSelected(false);
        Image i = m.getImage();
        if (i != null)
            this.jLabel15.setIcon(new ImageIcon(i));

        this.m = m;
    }

    private void saveActorRoles(Movie m) {
        // Actors
        List<AddSubtractInnerPanel> panels = this.addSubstractActorsPanel.
                getInnerPanelsValues();
       
        for (AddSubtractInnerPanel panel : panels) {

            JComboBox actorBox = (JComboBox) panel
                            .getLeftComboObject();
            JComboBox roleBox = (JComboBox) panel
                            .getRightComboObject();
            ActorComboBoxModel actorModel = (ActorComboBoxModel) actorBox
                            .getModel();
            int selectedIndex = ((JComboBox) panel
                            .getLeftComboObject()).getSelectedIndex();
            Actor actor = (Actor) actorModel
                            .getActorAt(selectedIndex);
            String role = (String) roleBox.getSelectedItem();
            String character = panel.getTextFieldText();

            MangoController.getInstance().addActorToMovie(m, actor, role, 
                    character);
        }
    }

    /**
     * This method is needed to set up the add actor panel and other
     * comboboxes.
     */
    private void setCombBoxModels() {
        this.addSubstractActorsPanel.setComboBoxPrefs(
                new ActorComboBoxModel(), new RoleComboBoxModel(),
                false, true);
        this.ownerCB.setModel(new PersonComboBoxModel());
        this.borrowerCB.setModel(new PersonComboBoxModel());

    }

    /**
     * Gets the genres for a movie and creates a comma separated list.
     * 
     * @param m
     *            The movie to grab the genres from.
     * @return The comma separated list of genres for a
     *         <code>Movie m</code>
     */
    private String genreText(Movie m) {
        String tmp = "";
        List<String> str = m.getGenres();
        for (String s : str) {
            tmp += s + ", ";
        }
        if (tmp.length() > 0) {
            tmp = tmp.substring(0, tmp.length() - 2);
        }
        return tmp;
    }

    /**
     * This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        movieInfoPane = new javax.swing.JPanel();
        topInfoComponentsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        titleTF = new javax.swing.JTextField();
        directorTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ratingCB = new javax.swing.JComboBox();
        runtimeSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        yearTF = new javax.swing.JTextField();
        middleInfoComponentsPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        purchaseDateTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        typeCB = new javax.swing.JComboBox();
        mangoRatingCB = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        conditionCB = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        customDescriptionScrollPane = new javax.swing.JScrollPane();
        customDescriptionTA = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        genreTF = new javax.swing.JTextField();
        bottomInfoComponentsPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        amazonRetrieveButton = new javax.swing.JButton();
        asinTF = new javax.swing.JTextField();
        associationsPane = new javax.swing.JPanel();
        setOwnerBorrowerPane = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        ownerCB = new javax.swing.JComboBox();
        borrowerCB = new javax.swing.JComboBox();
        borrowedCheckBox = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        addPersonPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        phoneNumberTF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        addressScrollPane = new javax.swing.JScrollPane();
        AddressTA = new javax.swing.JTextArea();
        addPersonButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        actorsPane = new javax.swing.JPanel();
        actingRolesPane = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        addSubstractActorsPanel = new com.themangoproject.ui.AddSubtractPanel();
        addActorPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        actorFirstNameTF = new javax.swing.JTextField();
        actorLastNameTF = new javax.swing.JTextField();
        addActorButton = new javax.swing.JButton();
        artworkPane = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        changeThumbnailButton = new javax.swing.JButton();
        defaultImageButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        queryAmazonButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add/Edit Movie");
        setIconImage(null);
        setResizable(false);

        topInfoComponentsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Title");

        jLabel2.setText("Director");

        jLabel3.setText("Year (YYYY)");

        jLabel4.setText("Rating");

        jLabel5.setText("Run Time");

        ratingCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "G", "PG", "PG-13", "NC-17", "R", "NR", "Unrated", "RP", "TV-Y7", "TV-Y7FV", "TV-G", "TV-PG", "TV-14", "TV-MA" }));

        jLabel6.setText("minutes");

        javax.swing.GroupLayout topInfoComponentsPanelLayout = new javax.swing.GroupLayout(topInfoComponentsPanel);
        topInfoComponentsPanel.setLayout(topInfoComponentsPanelLayout);
        topInfoComponentsPanelLayout.setHorizontalGroup(
            topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topInfoComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topInfoComponentsPanelLayout.createSequentialGroup()
                        .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(topInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(titleTF, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(topInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(222, 222, 222)))
                        .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(directorTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topInfoComponentsPanelLayout.createSequentialGroup()
                        .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(yearTF, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ratingCB, 0, 153, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(topInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(runtimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))))
                .addContainerGap())
        );
        topInfoComponentsPanelLayout.setVerticalGroup(
            topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topInfoComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(directorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(runtimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ratingCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        middleInfoComponentsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Purchase Date (MM/DD/YYYY)");

        jLabel9.setText("Type");

        jLabel10.setText("Mango Rating");

        typeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blu-ray", "Digtial", "DVD", "HD-DVD", "VCD", "VHS" }));

        mangoRatingCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));

        jLabel11.setText("Condition");

        jLabel12.setText("Custom Description");

        customDescriptionTA.setColumns(20);
        customDescriptionTA.setLineWrap(true);
        customDescriptionTA.setRows(5);
        customDescriptionScrollPane.setViewportView(customDescriptionTA);

        jLabel24.setText("Genre (separate with commas)");

        javax.swing.GroupLayout middleInfoComponentsPanelLayout = new javax.swing.GroupLayout(middleInfoComponentsPanel);
        middleInfoComponentsPanel.setLayout(middleInfoComponentsPanelLayout);
        middleInfoComponentsPanelLayout.setHorizontalGroup(
            middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middleInfoComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middleInfoComponentsPanelLayout.createSequentialGroup()
                        .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(middleInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10))
                            .addGroup(middleInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(purchaseDateTF, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mangoRatingCB, 0, 153, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeCB, 0, 147, Short.MAX_VALUE)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel12)
                    .addComponent(customDescriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middleInfoComponentsPanelLayout.createSequentialGroup()
                        .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(middleInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(conditionCB, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(middleInfoComponentsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(197, 197, 197)))
                        .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(genreTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
                .addContainerGap())
        );
        middleInfoComponentsPanelLayout.setVerticalGroup(
            middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middleInfoComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchaseDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mangoRatingCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(middleInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conditionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customDescriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottomInfoComponentsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("ASIN");

        amazonRetrieveButton.setText("Get Information Via Amazon");
        amazonRetrieveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amazonRetrieveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomInfoComponentsPanelLayout = new javax.swing.GroupLayout(bottomInfoComponentsPanel);
        bottomInfoComponentsPanel.setLayout(bottomInfoComponentsPanelLayout);
        bottomInfoComponentsPanelLayout.setHorizontalGroup(
            bottomInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomInfoComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(bottomInfoComponentsPanelLayout.createSequentialGroup()
                        .addComponent(asinTF, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amazonRetrieveButton)))
                .addContainerGap())
        );
        bottomInfoComponentsPanelLayout.setVerticalGroup(
            bottomInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomInfoComponentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomInfoComponentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(amazonRetrieveButton)
                    .addGroup(bottomInfoComponentsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(asinTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        bottomInfoComponentsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {amazonRetrieveButton, asinTF});

        javax.swing.GroupLayout movieInfoPaneLayout = new javax.swing.GroupLayout(movieInfoPane);
        movieInfoPane.setLayout(movieInfoPaneLayout);
        movieInfoPaneLayout.setHorizontalGroup(
            movieInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movieInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(movieInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(middleInfoComponentsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottomInfoComponentsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addComponent(topInfoComponentsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        movieInfoPaneLayout.setVerticalGroup(
            movieInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topInfoComponentsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middleInfoComponentsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomInfoComponentsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab("Movie Info", movieInfoPane);

        setOwnerBorrowerPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Owner");

        ownerCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        borrowerCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        borrowerCB.setEnabled(false);

        borrowedCheckBox.setText("Borrowed");
        borrowedCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                borrowedCheckBoxItemStateChanged(evt);
            }
        });

        jLabel18.setText("Borrower");

        javax.swing.GroupLayout setOwnerBorrowerPaneLayout = new javax.swing.GroupLayout(setOwnerBorrowerPane);
        setOwnerBorrowerPane.setLayout(setOwnerBorrowerPaneLayout);
        setOwnerBorrowerPaneLayout.setHorizontalGroup(
            setOwnerBorrowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setOwnerBorrowerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(setOwnerBorrowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(setOwnerBorrowerPaneLayout.createSequentialGroup()
                        .addComponent(ownerCB, 0, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(setOwnerBorrowerPaneLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(170, 170, 170)))
                .addGroup(setOwnerBorrowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(setOwnerBorrowerPaneLayout.createSequentialGroup()
                        .addComponent(borrowerCB, 0, 173, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borrowedCheckBox))
                    .addComponent(jLabel18))
                .addContainerGap())
        );
        setOwnerBorrowerPaneLayout.setVerticalGroup(
            setOwnerBorrowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setOwnerBorrowerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(setOwnerBorrowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(setOwnerBorrowerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ownerCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrowedCheckBox)
                    .addComponent(borrowerCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addPersonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Create New Person"));

        jLabel19.setText("Name");

        jLabel20.setText("Phone Number");

        jLabel21.setText("Address");

        AddressTA.setColumns(20);
        AddressTA.setLineWrap(true);
        AddressTA.setRows(5);
        addressScrollPane.setViewportView(AddressTA);

        addPersonButton.setText("Add Person");
        addPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonButtonActionPerformed(evt);
            }
        });

        jLabel25.setText("E-Mail");

        javax.swing.GroupLayout addPersonPanelLayout = new javax.swing.GroupLayout(addPersonPanel);
        addPersonPanel.setLayout(addPersonPanelLayout);
        addPersonPanelLayout.setHorizontalGroup(
            addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPersonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailTF, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addGroup(addPersonPanelLayout.createSequentialGroup()
                        .addGroup(addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPersonPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(211, 211, 211))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPersonPanelLayout.createSequentialGroup()
                                .addComponent(nameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(phoneNumberTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                    .addComponent(jLabel21)
                    .addComponent(jLabel25)
                    .addComponent(addPersonButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
                .addContainerGap())
        );
        addPersonPanelLayout.setVerticalGroup(
            addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPersonPanelLayout.createSequentialGroup()
                .addGroup(addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPersonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addressScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(addPersonButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout associationsPaneLayout = new javax.swing.GroupLayout(associationsPane);
        associationsPane.setLayout(associationsPaneLayout);
        associationsPaneLayout.setHorizontalGroup(
            associationsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, associationsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(associationsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addPersonPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(setOwnerBorrowerPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        associationsPaneLayout.setVerticalGroup(
            associationsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(associationsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setOwnerBorrowerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPersonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Associations", associationsPane);

        actingRolesPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Role");

        jLabel16.setText("Character");

        jLabel13.setText("Actor");

        javax.swing.GroupLayout actingRolesPaneLayout = new javax.swing.GroupLayout(actingRolesPane);
        actingRolesPane.setLayout(actingRolesPaneLayout);
        actingRolesPaneLayout.setHorizontalGroup(
            actingRolesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actingRolesPaneLayout.createSequentialGroup()
                .addGroup(actingRolesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(actingRolesPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel14)
                        .addGap(98, 98, 98)
                        .addComponent(jLabel16))
                    .addGroup(actingRolesPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addSubstractActorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        actingRolesPaneLayout.setVerticalGroup(
            actingRolesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actingRolesPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actingRolesPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSubstractActorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        addActorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Actor"));

        jLabel22.setText("First Name");

        jLabel23.setText("Last Name");

        addActorButton.setText("Add Actor");
        addActorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addActorPanelLayout = new javax.swing.GroupLayout(addActorPanel);
        addActorPanel.setLayout(addActorPanelLayout);
        addActorPanelLayout.setHorizontalGroup(
            addActorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addActorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addActorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addActorPanelLayout.createSequentialGroup()
                        .addComponent(actorFirstNameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(addActorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(135, 135, 135)))
                .addGroup(addActorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addActorPanelLayout.createSequentialGroup()
                        .addComponent(actorLastNameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addActorButton))
                    .addComponent(jLabel23))
                .addContainerGap())
        );
        addActorPanelLayout.setVerticalGroup(
            addActorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addActorPanelLayout.createSequentialGroup()
                .addGroup(addActorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addActorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actorFirstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addActorButton)
                    .addComponent(actorLastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout actorsPaneLayout = new javax.swing.GroupLayout(actorsPane);
        actorsPane.setLayout(actorsPaneLayout);
        actorsPaneLayout.setHorizontalGroup(
            actorsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actorsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actorsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(actingRolesPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addActorPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        actorsPaneLayout.setVerticalGroup(
            actorsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actorsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actingRolesPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addActorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab("Actors", actorsPane);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        changeThumbnailButton.setText("Change Thumbnail");
        changeThumbnailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeThumbnailButtonActionPerformed(evt);
            }
        });

        defaultImageButton.setText("Default Image");
        defaultImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout artworkPaneLayout = new javax.swing.GroupLayout(artworkPane);
        artworkPane.setLayout(artworkPaneLayout);
        artworkPaneLayout.setHorizontalGroup(
            artworkPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, artworkPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(artworkPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addGroup(artworkPaneLayout.createSequentialGroup()
                        .addComponent(defaultImageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(changeThumbnailButton)))
                .addContainerGap())
        );
        artworkPaneLayout.setVerticalGroup(
            artworkPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, artworkPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(artworkPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeThumbnailButton)
                    .addComponent(defaultImageButton))
                .addContainerGap())
        );

        tabbedPane.addTab("Artwork", artworkPane);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        queryAmazonButton.setText("Query Amazon");
        queryAmazonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryAmazonButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(queryAmazonButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(queryAmazonButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void queryAmazonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryAmazonButtonActionPerformed
    amazonQueryFrame.setLocationRelativeTo(this);
    amazonQueryFrame.setVisible(true);
}//GEN-LAST:event_queryAmazonButtonActionPerformed

    private void cancelButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
        // Cancel Button - Discard any unsaved changes.
        this.dispose();
    }// GEN-LAST:event_cancelButtonActionPerformed

    private void saveButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
        // Save button

        String title = this.titleTF.getText();
        String director = this.directorTF.getText();
        String rating = (String) this.ratingCB.getSelectedItem();
        int runtime = ((Integer) this.runtimeSpinner.getValue())
                .intValue();
        if (runtime < 0)
            runtime = -1;
        int year = -1;
        int mangorating = -1;
        try {
            year = Integer.parseInt(this.yearTF.getText());
            mangorating = Integer
                    .parseInt((String) this.mangoRatingCB
                            .getSelectedItem());
        } catch (NumberFormatException e) {
            year = -1;
            mangorating = 0;
        }
        String asin = this.asinTF.getText();
        String[] purchDateString = this.purchaseDateTF.getText()
                .split("/");
        java.sql.Date purchDate;
        if (purchDateString.length == 3) {
            int pYear = 0;
            int month = 0;
            int day = 0;
            try {
                pYear = Integer.parseInt(purchDateString[2]);
                month = Integer.parseInt(purchDateString[0]) - 1;
                day = Integer.parseInt(purchDateString[1]);
            } catch (NumberFormatException e) {
                purchDate = null;
            }
            if (month < 0 || month > 11)
                purchDate = null;
            else {
                long date = new GregorianCalendar(pYear, month, day)
                        .getTimeInMillis();
                purchDate = new java.sql.Date(date);
            }
        } else {
            purchDate = null;
        }

        String custDesc = this.customDescriptionTA.getText();
        String condition = conditionCB.getText();
        String type = (String) typeCB.getSelectedItem();

        // New movie
        if (m == null) {
            Movie mov = null;
            mov = MangoController.getInstance().addMovie(title,
                    director, rating, runtime, year, asin, purchDate,
                    custDesc, condition, type, mangorating);
            // Now we wanna add the image to the DB for the movie
            if (mov != null) { // Add other information
                // Genre
                String[] gens = this.genreTF.getText().split(", *");
                for (String g : gens) {
                    MangoController.getInstance().addGenreToMovie(
                            mov, g);
                }
                
                // Actors
                this.saveActorRoles(mov);
                
                // Owner and borrower
                PersonComboBoxModel ownerModel = (PersonComboBoxModel) ownerCB
                        .getModel();
                PersonComboBoxModel borrowerModel = (PersonComboBoxModel) borrowerCB
                        .getModel();
                int selectedIndexO = ownerCB.getSelectedIndex();
                int selectedIndexB = borrowerCB.getSelectedIndex();
                Person owner, borrower;
                if (selectedIndexO == -1)
                    owner = null;
                else
                    owner = (Person) ownerModel
                            .getPersonAt(selectedIndexO);
                if (selectedIndexB == -1
                        || !this.borrowedCheckBox.isSelected())
                    borrower = null;
                else
                    borrower = (Person) borrowerModel
                            .getPersonAt(selectedIndexB);
                mov.setTitle(mov.getTitle());
                mov.setOwner(owner);
                mov.setBorrower(borrower);
                MangoController.getInstance().updateMovie(mov);

                // Image
                Image i = ((ImageIcon) jLabel15.getIcon()).getImage();
                BufferedImage bi = Pictures.toBufferedImage(i);
                try {
                    ImageIO.write(bi, "jpg", new File("temp.jpg"));
                    MangoController.getInstance().setImageForMovie(
                            mov, new FileInputStream("temp.jpg"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(this,
                    "The movie was successfully added.",
                    "Save Successfull",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            m.setTitle(title);
            m.setDirector(director);
            m.setRating(rating);
            m.setRuntime(runtime);
            m.setYear(year);
            m.setASIN(asin);
            m.setPurchaseDate(purchDate);
            m.setCustomDescription(custDesc);
            m.setCondition(condition);
            m.setType(type);
            m.setMangoRating(mangorating);
            MangoController.getInstance().updateMovie(m);

            // Genre
            String[] gens = this.genreTF.getText().split(", *");
            List<String> gensInTF = new ArrayList<String>();
            for (int i = 0; i < gens.length; i++) {
                gensInTF.add(gens[i]);
            }
            List<String> gensInDB = m.getGenres();
            for (String g : gens) {
                if (!gensInDB.contains(g))
                    MangoController.getInstance().addGenreToMovie(m,
                            g);
            }
            for (String g : gensInDB) {
                if (!gensInTF.contains(g))
                    MangoController.getInstance()
                            .removeGenreFromMovie(m, g);
            }
            
            // Because this is a movie that already exists, we want to remove
            // all of the old roles and we will just add new ones from the new
            // information provided in the edited dialog.
            MangoController.getInstance().deleteRolesForMovie(m);
            saveActorRoles(m);
           
            // Owner and borrower
            PersonComboBoxModel ownerModel = (PersonComboBoxModel) ownerCB
                    .getModel();
            PersonComboBoxModel borrowerModel = (PersonComboBoxModel) borrowerCB
                    .getModel();
            int selectedIndexO = ownerCB.getSelectedIndex();
            int selectedIndexB = borrowerCB.getSelectedIndex();
            Person owner, borrower;
            if (selectedIndexO == -1)
                owner = null;
            else
                owner = (Person) ownerModel
                        .getPersonAt(selectedIndexO);
            if (selectedIndexB == -1
                    || !this.borrowedCheckBox.isSelected())
                borrower = null;
            else
                borrower = (Person) borrowerModel
                        .getPersonAt(selectedIndexB);
            MangoController.getInstance().setOwnerToMovie(m, owner);
            MangoController.getInstance().setBorrowerToMovie(m,
                    borrower);
//            System.out.println(((DBMovie) m).getOwnerId());
//            System.out.println(((DBMovie) m).getBorrowerId());
            MangoController.getInstance().updateMovie(m);

            // Image
            Image i = ((ImageIcon) jLabel15.getIcon()).getImage();
            BufferedImage bi = Pictures.toBufferedImage(i);
            try {
                ImageIO.write(bi, "jpg", new File("temp.jpg"));
                MangoController.getInstance().setImageForMovie(m,
                        new FileInputStream("temp.jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this,
                    "The movie was successfully updated.",
                    "Update Successfull",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // Close after exit
        this.dispose();

    }// GEN-LAST:event_saveButtonActionPerformed

    private void changeThumbnailButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_changeThumbnailButtonActionPerformed
        // Upload image button code.
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new ImageFilter());
        jfc.setAcceptAllFileFilterUsed(false);
        int i = jfc.showOpenDialog(this);

        if (i == JFileChooser.APPROVE_OPTION) {
            Image image = null;
            try {
                BufferedImage im = ImageIO
                        .read(jfc.getSelectedFile());
                image = im.getScaledInstance(160, 160,
                        Image.SCALE_DEFAULT);
                this.jLabel15.setIcon(new ImageIcon(image));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(addPersonButton,
                        "Error uploading picture");
                Logger.getLogger(BottomBar.class.getName()).log(
                        Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(addPersonButton,
                        "Error uploading picture");
                Logger.getLogger(BottomBar.class.getName()).log(
                        Level.SEVERE, null, ex);
            }

        }
    }// GEN-LAST:event_changeThumbnailButtonActionPerformed

    private void amazonRetrieveButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_amazonRetrieveButtonActionPerformed
        // Get information from amazon.
        int proceed = JOptionPane
                .showConfirmDialog(
                        this,
                        "Mango will attempt to gather information from Amazon.com.  The "
                                + "provided information may not be\naccurate.  Any information already "
                                + "filled out may be altered.  Do you want to proceed?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
        if (proceed == JOptionPane.YES_OPTION) {
        	amazonWorker.execute();
        }
    }// GEN-LAST:event_amazonRetrieveButtonActionPerformed

    private void addPersonButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addPersonButtonActionPerformed
        // Adds a new person to the database if it doesn't exist.
        boolean added = false;
        try {
            // Create a new person.
            if (!this.nameTF.getText().equals(""))
                added = MangoController.getInstance().addPerson(
                        this.nameTF.getText(),
                        this.phoneNumberTF.getText(),
                        this.emailTF.getText(),
                        this.AddressTA.getText());
            if (added) {
                JOptionPane.showMessageDialog(this, this.nameTF
                        .getText()
                        + " was added as a person.", "Person Added",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, this.nameTF
                        .getText()
                        + " already exists.",
                        "Person Already Exists",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (PersonExistsException ex) {
            JOptionPane.showMessageDialog(this, this.nameTF
                    + " already exists", "Person Already exists",
                    JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_addPersonButtonActionPerformed

    private void defaultImageButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_defaultImageButtonActionPerformed
        // Reset Thumbnail to default image Default Image
        int proceed = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reset the thumbnail image to the default "
                        + "image?", "Reset Thumbnail to Default",
                JOptionPane.YES_NO_OPTION);
        if (proceed == JOptionPane.YES_OPTION) {
            Image im = new ImageIcon(
                    getClass()
                            .getResource(
                                    "/com/themangoproject/ui/images/defaultMangoLogo.jpg"))
                    .getImage();

            this.jLabel15.setIcon(new ImageIcon(im.getScaledInstance(
                    160, 160, Image.SCALE_DEFAULT)));

        }
    }// GEN-LAST:event_defaultImageButtonActionPerformed

    private void borrowedCheckBoxItemStateChanged(
            java.awt.event.ItemEvent evt) {// GEN-FIRST:event_borrowedCheckBoxItemStateChanged
        // Borrowed check box - disables the borrower combobox if no
        // checked.
        Object source = evt.getItemSelectable();
        int state = evt.getStateChange();
        if (source == this.borrowedCheckBox) {
            if (state == ItemEvent.SELECTED)
                this.borrowerCB.setEnabled(true);
            else
                this.borrowerCB.setEnabled(false);
        }
    }// GEN-LAST:event_borrowedCheckBoxItemStateChanged

    private void addActorButtonActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addActorButtonActionPerformed
        // Add new Actor to the database if it doesn't exist
        boolean added = false;
        if (!this.actorFirstNameTF.getText().equals("")
                && !this.actorLastNameTF.getText().equals(""))
            added = MangoController.getInstance().addActor(
                    this.actorFirstNameTF.getText(),
                    this.actorLastNameTF.getText());
        if (added) {
            JOptionPane.showMessageDialog(this, this.actorFirstNameTF
                    .getText()
                    + " "
                    + this.actorLastNameTF.getText()
                    + " was added as an actor.", "Actor Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, this.actorFirstNameTF
                    .getText()
                    + " "
                    + this.actorLastNameTF.getText()
                    + " already exists.", "Actor Already Exists",
                    JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_addActorButtonActionPerformed

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MovieAddEditDialog dialog = new MovieAddEditDialog(
                        new javax.swing.JFrame(), true);
                dialog
                        .addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosing(
                                    java.awt.event.WindowEvent e) {
                                System.exit(0);
                            }
                        });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AddressTA;
    private javax.swing.JPanel actingRolesPane;
    private javax.swing.JTextField actorFirstNameTF;
    private javax.swing.JTextField actorLastNameTF;
    private javax.swing.JPanel actorsPane;
    private javax.swing.JButton addActorButton;
    private javax.swing.JPanel addActorPanel;
    private javax.swing.JButton addPersonButton;
    private javax.swing.JPanel addPersonPanel;
    private com.themangoproject.ui.AddSubtractPanel addSubstractActorsPanel;
    private javax.swing.JScrollPane addressScrollPane;
    private javax.swing.JButton amazonRetrieveButton;
    private javax.swing.JPanel artworkPane;
    private javax.swing.JTextField asinTF;
    private javax.swing.JPanel associationsPane;
    private javax.swing.JCheckBox borrowedCheckBox;
    private javax.swing.JComboBox borrowerCB;
    private javax.swing.JPanel bottomInfoComponentsPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton changeThumbnailButton;
    private javax.swing.JTextField conditionCB;
    private javax.swing.JScrollPane customDescriptionScrollPane;
    private javax.swing.JTextArea customDescriptionTA;
    private javax.swing.JButton defaultImageButton;
    private javax.swing.JTextField directorTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField genreTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox mangoRatingCB;
    private javax.swing.JPanel middleInfoComponentsPanel;
    private javax.swing.JPanel movieInfoPane;
    private javax.swing.JTextField nameTF;
    private javax.swing.JComboBox ownerCB;
    private javax.swing.JTextField phoneNumberTF;
    private javax.swing.JTextField purchaseDateTF;
    private javax.swing.JButton queryAmazonButton;
    private javax.swing.JComboBox ratingCB;
    private javax.swing.JSpinner runtimeSpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel setOwnerBorrowerPane;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField titleTF;
    private javax.swing.JPanel topInfoComponentsPanel;
    private javax.swing.JComboBox typeCB;
    private javax.swing.JTextField yearTF;
    // End of variables declaration//GEN-END:variables

}
