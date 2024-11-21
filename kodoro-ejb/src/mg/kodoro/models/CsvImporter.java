package mg.kodoro.models;

import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import mg.kodoro.models.production.AdminProduction;
import mg.kodoro.models.production.Production;
import utilitaire.UtilDB;

    public class CsvImporter {
        public static void importCsvData(String filePath, Connection connection) {
            String line;
            int numLine = 2;
            String delimiter = ",";
            Connection conn = new UtilDB().GetConn();
            try (
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                ) {
                conn.setAutoCommit(false);
                // Lire la première ligne (en-têtes) et l'ignorer
                br.readLine();
    
                // Lire les lignes suivantes
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(delimiter);
                    if (values.length != 6) {
                        System.err.println("Ligne ignorée : " + line);
                        numLine += 1;
                        continue; // Ignorer les lignes incorrectes
                    }
                    // Convertir et assigner les valeurs
                    String date = (values[0].trim());
                    String longValue = (values[1].trim());
                    String largValue = (values[2].trim());
                    String hautValue = (values[3].trim());
                    String revientValue = (values[4].trim());
                    String machine = values[5].trim();
                    try {
                        AdminProduction adminProduction = new AdminProduction(longValue, largValue, hautValue, date, revientValue, machine);
                        Production prod = adminProduction.produire(conn);
                        System.out.println("=> "+prod);
                        numLine += 1;
                    } catch (Exception e) {
                        System.err.println("Erreur lors du traitement de la ligne : " + line);
                        throw e;
                    }
                }
                System.out.println("Importation des données CSV terminée.");
            } catch (Exception e1) {
                try {
                    conn.rollback();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                e1.printStackTrace();
            }
            finally {
                System.out.println("NUM LINE : "+numLine);
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }