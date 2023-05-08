import mysql.connector

# Crear conexion/cursor (cambiar parametros en caso de ser necesario)
conexion = mysql.connector.connect(user='root', password='1234',
host='localhost', database='battle', port='3306')
cursor = conexion.cursor()
cursor.execute("Select * from battle;")

# Recorrer cursor a traves de la variable battle
battle = cursor.fetchone()

# Crear archivo battle.xml
fichero = open("battle.xml", "w")

# Añadir las batallas al archivo
fichero.write("<?xml version='1.0' encoding='UTF-8'?>\n")
fichero.write("<battles>\n")
while battle:
    # <battle id=''>
    open_battle = ("<battle id='" + str(battle[0]) + "'>\n")
    fichero.write(open_battle)

    # Campos restantes
    add_player_id = ("<player_id>" + str(battle[1]) + "</player_id>\n")
    add_warrior_id = ("<warrior_id>" + str(battle[2]) + "</warrior_id>\n")
    add_warrior_weapon_id = ("<warrior_weapon_id>" + str(battle[3]) + "</warrior_weapon_id>\n")
    add_opponent_id = ("<opponent_id>" + str(battle[4]) + "</opponent_id>\n")
    add_opponent_weapon_id = ("<opponent_weapon_id>" + str(battle[5]) + "</opponent_weapon_id>\n")
    add_injuries_caused = ("<injuries_caused>" + str(battle[6]) + "</injuries_caused>\n")
    add_injuries_suffered = ("<injuries_suffered>" + str(battle[7]) + "</injuries_suffered>\n")
    add_battle_points = ("<battle_points>" + str(battle[8]) + "</battle_points>\n")

    fichero.write(add_player_id)
    fichero.write(add_warrior_id)
    fichero.write(add_warrior_weapon_id)
    fichero.write(add_opponent_id)
    fichero.write(add_opponent_weapon_id)
    fichero.write(add_injuries_caused)
    fichero.write(add_injuries_suffered)
    fichero.write(add_battle_points)

    # </battle>
    close_battle = "</battle>\n"
    fichero.write(close_battle)

    battle = cursor.fetchone()

fichero.write("</battles>\n")
fichero.close()
