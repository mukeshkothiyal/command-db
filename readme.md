<h2>Command DB</h2>
A command line springboot application (using standard JDBC, spring JDBC variants) that can be used to execute script against any generic Db.
A very simplified dumbed-down version of a generic db client.

<h4>Usage</h4>
Can be used to test connectivity, driver configuration in Java based applications

<h4>Compatibility</h4>
Current support is for postgres, edb, h2 (embedded)

<h4>Sample Data Initialization</h4>
<h5>For H2 DB</h5>
<li>
<b>DDLs : </b>
schema-h2.sql
</li>
<li>
<b>Dataset</b>
data-h2.sql
</li>

<h4>Command Line Execution</h4>
Require 2 command-line parameters for execution:
<li>parameter 1: query type: "C"-Create, "R"-Retrieve</li>
<li>parameter 2: query string</li>
<p>
Command Format: 
<br/>
<code>java -jar target/command-db-0.0.1-SNAPSHOT.JAR [type] [query]</code>
<p/>
Example: 
<br/>
<code>java -jar target/command-db-0.0.1-SNAPSHOT.jar "R" "select * from departments"</code>