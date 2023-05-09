Instruccions per veure el registre de batalles en format html:
---------------------------------------------------------------------------------------------------------------------------
1-Descarregar la carpeta de M4
2-Obrir un terminal o un cmd depenent del nostre SO (cmd -> Windows | terminal -> Linux), com a root o administradors
3-Descarregar el mysql-connector executant les seguents comandes depenent del nostre SO:
  WINDOWS  -> 'py -m pip install mysql-connector-python'
  LINUX  ->  'apt-get install python3-pip' i després 'pip install mysql-connector-python'
  
4-Descarregar el lxml executant les seguents comandes depenent del nostre SO:
  WINDOWS  ->  'py -m pip install lxml'
  LINUX  ->  'pip install lxml'
  
5-Anar al directori on tenim carpeta descarregada 'M4' i actualitzar els fitxers xml i html amb les dades de la BBDD:
  WINDOWS  ->  'dataToXML.py' i després 'xmlToHtml.py'
  LINUX  ->  'python3 dataToXML.py' i després 'python3 xmlToHtml.py'
  
6-Ara tindrem que obrir la carpeta 'M4' al programa Visual Studio Code, dins d'aquest seleccionar 'terminal' -> 'new terminal'.
7-Dins d'aquest terminal executem:
  ->  'python3 -m http.server'
  
8-Obrir [localhost:8000/html/battle.html](localhost:8000/html/battle.html) al navegador.
 
