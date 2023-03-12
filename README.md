# Data Processing : Unstructured data into SQLite DB
<h2><b>Java applications to convert unstructured data into SQLite databases</b></h2>

<b><h3>Amazon Search json and csv parser :</h3></b>

It parses both csv and json files. The json files are in a .gz (gzip) format
The code reads both types of files and parses them accordingly.
Since each file is very huge in size (~ 1GB) they are <b>parsed in a serial</b> fashion.

<b><h3>Keepa data json processor :</h3></b>

It parses json data, extracts required information and stores to a SQLite databse.
Here file is small in size () si it is in .json format.
Since each file is small, they are <b>parsed in parallel</b> and 100 files are dumped to DB at a time.


</br>Both Java applications are built using <b>maven build tool</b>. 
</br><b>Package type : jar </b>
</br>Once packaged they were deployed to <b>Google Cloud Platform</b>
