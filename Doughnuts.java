import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Doughnuts
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int doughnutId;
    public String doughnutName;
    public String doughnutFilling;
    public int doughnutCalories;
    public int doughnutPrice;
    public int doughnutTopping;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Doughnuts(int doughnutId, String doughnutName, String doughnutFilling, int doughnutCalories, int doughnutPrice, int doughnutTopping)
    {
        this.dougnutId = doughnutId;        
        this.doughnutName = doughnutName;
        this.doughnutFilling = doughnutFilling;
        this.doughnutCalories = doughnutCalories;
        this.doughnutPrice = doughnutPrice;
        this.doughnutTopping = doughnutTopping;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. */
    @Override public String toString()
    {
        return name;
    }

    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */
    public static void readAll(List<Doughnuts> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT doughnutId, doughnutName, doughnutFilling, doughnutCalories, doughnutPrice, doughnutTopping FROM Doughnuts ORDER BY doughnutId"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Doughnuts(results.getInt("doughnutId"), results.getString("doughnutName"), results.getString("doughnutFilling"), results.getInt("doughnutCalories"), results.getInt("doughnutPrice"), results.getInt("doughnutTopping")));
                        
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public static Doughnuts getById(int doughnutId)
    {
        Dougnuts doughnuts = null;

        PreparedStatement statement = Application.database.newStatement("SELECT doughnutId, doughnutName, doughnutPrice FROM Doughnuts WHERE id = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, id);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    Doughnuts = new Doughnuts(results.getInt("doughnutId"), results.getString("doughnutName"), results.getInt("doughnutPrice"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return thing;
    }

    public static void deleteById(int doughnutId)
    {
        try 
        {

            PreparedStatement statement = Application.database.newStatement("DELETE FROM Doughnuts WHERE doughnutId = ?");             
            statement.setInt(1, doughnutId);

            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }
    
    public void save()    
    {
        PreparedStatement statement;

        try 
        {

            if (id == 0)
            {

                statement = Application.database.newStatement("SELECT doughnutId FROM Doughnuts ORDER BY doughnutId DESC");             

                if (statement != null)	
                {
                    ResultSet results = Application.database.runQuery(statement);
                    if (results != null)
                    {
                        id = results.getInt("doughnutId") + 1;
                    }
                }

                statement = Application.database.newStatement("INSERT INTO Dougnuts (doughnutId, doughnutName, doughnutFilling, doughnutCalories, doughnutPrice, doughnutTopping) VALUES (?, ?, ?)");             
                statement.setInt(1, doughnutId);
                statement.setString(2, doughnutName);
                statement.setString(3, doughnutFilling);
                statement.setInt(4, doughnutCalories);
                statement.setInt(5, doughnutPrice);
                statement.setInt(6, doughnutTopping);

            }
            else
            {
                statement = Application.database.newStatement("UPDATE Doughnuts SET doughnutName = ?, doughnutPrice = ? WHERE doughnutId = ?");             
                statement.setString(1, doughnutName);
                statement.setInt(2, doughnutPrice);   
                statement.setInt(3, doughnutId);
            }

            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }

}
