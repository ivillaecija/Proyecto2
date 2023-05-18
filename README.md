Projecte2 - Izan Villaécija, Joel Martin i Brian Uscata

**INTRODUCCIO**
___________________________________________________________________________________________________________________________________
En aquest repositori hi han pujats diferents arxius per jugar a un joc de batalles entre diferents races, 
aquest joc esta desarrollat en java i compta amb una BBDD mysql per emmagatzenar els registres de les batalles
que juguem. També compta amb una carpeta en la qual podrem generar un arxiu html per poder veure totes les batalles
que s'han jugat fins ara més adecuadament, tot el contingut per jugar al joc i la pagina html de batalles es
pot trobar en la release.

**INSTRUCCIONS**
**BBDD**
___________________________________________________________________________________________________________________________________
Primer de tot tindrem que crear i configurar la BBDD que farem servir, per fer-ho tindrem que descarregar l'aplicació 
mysql(https://dev.mysql.com/downloads/installer/). Una vegada descarregada tindrem que crear una nova connexió fent click 
en el simbol de +, introduirem nom i credencials de la connexió. Una vegada hem creat la connexió tindrem que accedir a aquesta,
ara tindrem que copiar el contingut del fitxer 'todoenuno.sql' que trobarem en 'bbdd.zip' dins de la release i fer click
en la icone de executar, amb això ja haurem creat i configurat la BBDD.


**PROGRAMA**
___________________________________________________________________________________________________________________________________
Per inicar el joc tindrem que descarregar primer de tot l'eclipse(https://www.eclipse.org/downloads/packages/), una veagada 
descarregat l'inicem, dins d'aquest seleccionem file > import > directory i seleccionem la carpeta 'programa' resultant de 
descomprimir 'programa.zip' dins de la release. Una vegada hem importat el programa tindrem que seleccionar el projecte, fer 
click dret i seleccionar Build Path > configure build path > add external JARs, seleccionar el arxiu 'mysql-connector' dins de la 
release i aplicar els canvis. Una vegada fet aixó tindrem que anar al package ConnexionBBDD i canviar la linea 23, en compte de 1234 
posarem la contrasenya que hem configurat en la connexio del mysql. I per últim quan ja hem fet això anirem al package InterfazGrafica
i seleccionarem el fitxer.java ubicat en aquest, una vegada seleccionat farem click sobre la icona de executar. Amb aixó ja hauriem
iniciat el joc.


**PAGINA WEB BATALLES**
1-Descarregar la carpeta de M4
2-Obrir un terminal o un cmd depenent del nostre SO (cmd -> Windows | terminal -> Linux), com a root o administradors

3-Descarregar el mysql-connector executant les seguents comandes depenent del nostre SO: 
WINDOWS -> 'py -m pip install mysql-connector-python' LINUX -> 'apt-get install python3-pip' i després 
'pip install mysql-connector-python'

4-Descarregar el lxml executant les seguents comandes depenent del nostre SO: WINDOWS -> 'py -m pip install lxml' 
LINUX -> 'pip install lxml'

5-Anar al directori on tenim carpeta descarregada 'M4' i actualitzar els fitxers xml i html amb les dades de la BBDD: WINDOWS -> 'dataToXML.py' i després 'xmlToHtml.py' LINUX -> 'python3 dataToXML.py' i després 'python3 xmlToHtml.py'

6-Ara tindrem que obrir la carpeta 'M4' al programa Visual Studio Code, dins d'aquest seleccionar 'terminal' -> 'new terminal'.

7-Dins d'aquest terminal executem: -> 'python3 -m http.server'

En cas de que no funcioni provem executar la seguent: -> py -m http.server 7777

8-Obrir 'localhost:8000/html/battle.html' al navegador(o localhost:7777 depenent del pas 7).
