/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside fr.eriniumgroup.eriniumadventure.base as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package fr.eriniumgroup.eriniumadventure.base;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import java.sql.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForTest {
	public ForTest() {

		String test = new Object(){
			// Méthode pour récupérer une valeur à partir de la base de données
			private String recupererValeur(String url, String username, String password, String table, String column, String conditionValue) {
				String urlBaseDeDonnees = url;
				String nomUtilisateur = username;
				String motDePasse = password;
				String nomTable = table;
				String nomColonneValeur = column;
				String condition = conditionValue;

				if (!urlBaseDeDonnees.contains("http") && !urlBaseDeDonnees.contains("https")){
					urlBaseDeDonnees = "jdbc:mysql://" + url;
				}

				String valeur = null;
				Connection connexion = null;
				Statement statement = null;
				ResultSet resultSet = null;

				try {
					// Connexion à la base de données
					connexion = DriverManager.getConnection(urlBaseDeDonnees, nomUtilisateur, motDePasse);

					// Création de la requête SQL
					String requeteSQL = "SELECT " + nomColonneValeur + " FROM " + nomTable;
					if (!condition.isEmpty()) {
						requeteSQL += " WHERE " + condition;
					}

					// Création du statement
					statement = connexion.createStatement();

					// Exécution de la requête
					resultSet = statement.executeQuery(requeteSQL);

					// Récupération de la valeur
					if (resultSet.next()) {
						valeur = resultSet.getString(nomColonneValeur);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// Fermeture des ressources
					try {
						if (resultSet != null) {
							resultSet.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (connexion != null) {
							connexion.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				return valeur;
			}
		}.recupererValeur("URL", "USERNAME", "PASSWORD", "TABLE", "COLUMN", "CONDITION");
	}



	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		new ForTest();
	}

	@Mod.EventBusSubscriber
	private static class ForgeBusEvents {
		@SubscribeEvent
		public static void serverLoad(ServerStartingEvent event) {
		}

		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public static void clientLoad(FMLClientSetupEvent event) {
		}
	}
}
