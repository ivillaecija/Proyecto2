import mysql.connector

conexion = mysql.connector.connect(user='root', password='1234',
host='localhost', database='battle', port='3306')
cursor = conexion.cursor()
cursor.execute("Select * from battle;")
battle = cursor.fetchone()

fichero = open("battle.xml", "w")
while battle:

fichero.close()

