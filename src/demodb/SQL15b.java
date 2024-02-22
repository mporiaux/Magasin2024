    package demodb;

    import myconnections.DBConnection;

    import java.sql.CallableStatement;
    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.Types;
    import java.util.Scanner;

    public class SQL15b {

    public SQL15b() {
       Scanner sc =  new Scanner(System.in);
    Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        System.out.println("connexion établie");
       String query = "call APITELCLI(?,?)";
    try( CallableStatement statement = dbConnect.prepareCall(query);) {
       System.out.print("n° de client :");
       int nc = sc.nextInt();
    statement.setInt(1,nc);
    statement.registerOutParameter(2, Types.VARCHAR);
    //enregistrement du paramètre de sortie en fonction de son type et de son index
    statement.execute();
    //récupération du résultat en fonction de l'index
    String tel = statement.getString(2);
    System.out.println("tel du client = "+tel);


        }
    catch (SQLException e) {
        System.out.println("erreur SQL : code d'erreur = "+e.getErrorCode());
        System.out.println("erreur SQL : cause = "+e.getMessage());

    }
    catch (Exception e) { System.out.println("erreur ="+e);
    }
    DBConnection.closeConnection();
    }
    public static void main(String args[]) {
    SQL15b pgm = new SQL15b();
    }

    }
/*
create or replace PROCEDURE  "APITELCLI"
(
NUMRECH IN NUMBER
, TELRECH OUT VARCHAR2
) AS

BEGIN

select tel into telrech from apiclient where idclient=numrech;
EXCEPTION
 WHEN NO_DATA_FOUND THEN
   raise_application_error(-20001,'numéro de client inconnu');
END;

 */