package com.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connexion.BD.connexion;

public class PopPupArchiveTrame {

	// declaration
	private static Connection connect; // creer varaible connect
	JComboBox<String> listeTrame;
	JButton bArchiver, bAnnuler;

	public PopPupArchiveTrame() {
		Affichage();
	}

	private void Affichage() {
		String[] liste_Archive = { "Trame MP Archivée", "Trame PF_Archivée" };

		String Results = (String) JOptionPane.showInputDialog(null, "Quel Type de Trame Archive a Sauvegarder ?",
				"Transfert des Trame Base_Archive", JOptionPane.QUESTION_MESSAGE, null, liste_Archive,
				liste_Archive[0]);
		// choix de l'action
		if (Results == liste_Archive[0]) {
			// requete acces base de donnee
			try {
				connect = connexion.getConnection(); // initialisation connection
				String sql1 = "SELECT * FROM TABLE_ATMP ORDER BY NOM"; // selecton type de donne de la base
				PreparedStatement pState1 = (PreparedStatement) connect.prepareStatement(sql1);
				ResultSet rs = pState1.executeQuery();

				@SuppressWarnings("resource") // creation fichier excel
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet spreadsheet = wb.createSheet("base_Archive_TMP");
				XSSFRow row = spreadsheet.createRow(0);
				XSSFCell cell;

				// 1ere ligne avec nom des colonnes (recup nom via metadonne)
				for (int c = 1; c < 72; c++) {
					cell = row.createCell(c);
					cell.setCellValue(rs.getMetaData().getColumnName(c + 1));
				}
				// insertion suite de la table dans le fichier xls
				int i = 1; // pour la 2em ligne et suite
				while (rs.next()) {
					row = spreadsheet.createRow(i); // creeer la ligne
					for (int j = 1; j < 72; j++) { // boucle pour toute la ligne
						cell = row.createCell(j);
						cell.setCellValue(rs.getString(j + 1));
					}
					i++;
				}

				// choisir lieu de stockage
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				j.setDialogTitle("Destination du fichier de Backup");
				// pour accepter uniquement fichier excel
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Excel .xlsx", ".xlsx");
				j.addChoosableFileFilter(filter);
				j.setAcceptAllFileFilterUsed(false);
				j.setFileFilter(filter);

				int r = j.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					FileOutputStream out = new FileOutputStream(
							new File(j.getSelectedFile().getAbsolutePath().toString()));
					wb.write(out);
					JOptionPane.showMessageDialog(null, "Transfert Complete. Fichier Crée");
				} else {
					JOptionPane.showMessageDialog(null, "Aucune donnée n'a été transférée");
				}

				rs.close();
				pState1.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);

			}
		}

		if (Results == liste_Archive[1]) {
			// requete acces base de donnee
			try {
				connect = connexion.getConnection(); // initialisation connection
				String sql2 = "SELECT * FROM TABLE_ATPF ORDER BY NOM"; // selecton type de donne de la base
				PreparedStatement pState2 = (PreparedStatement) connect.prepareStatement(sql2);
				ResultSet rs = pState2.executeQuery();

				@SuppressWarnings("resource") // creation fichier excel
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet spreadsheet = wb.createSheet("base_Archive_TPF");
				XSSFRow row = spreadsheet.createRow(0);
				XSSFCell cell;

				// 1ere ligne avec nom des colonnes (recup nom via metadonne)
				for (int c = 1; c < 114; c++) {
					cell = row.createCell(c);
					cell.setCellValue(rs.getMetaData().getColumnName(c + 1));
				}
				// insertion suite de la table dans le fichier xls
				int i = 1; // pour la 2em ligne et suite
				while (rs.next()) {
					row = spreadsheet.createRow(i); // creeer la ligne
					for (int j = 1; j < 114; j++) { // boucle pour toute la ligne
						cell = row.createCell(j);
						cell.setCellValue(rs.getString(j + 1));
					}
					i++;
				}

				// choisir lieu de stockage
				JFileChooser j2 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				j2.setDialogTitle("Destination du fichier de Backup");
				// pour accepter uniquement fichier excel
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier Excel .xlsx", ".xlsx");
				j2.addChoosableFileFilter(filter);
				j2.setAcceptAllFileFilterUsed(false);
				j2.setFileFilter(filter);

				int r = j2.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					FileOutputStream out = new FileOutputStream(
							new File(j2.getSelectedFile().getAbsolutePath().toString()));
					wb.write(out);
					JOptionPane.showMessageDialog(null, "Transfert Complete. Fichier Crée");
				} else {
					JOptionPane.showMessageDialog(null, "Aucune donnée n'a été transférée");
				}

				rs.close();
				pState2.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);

			}
		} // fin du if

	} // fin du la methode
} // fin de classe
