#!/usr/bin/env python3
import sqlite3

def create_table_sensor(dbconnect, cursor):
    try:
        cursor.execute('''create table sensor (sensorID, type, zone)''')
        dbconnect.commit()
    except:
        print("sensor already exists")

if __name__ == "__main__":
    #some initial data
    id = 4
    temperature = 0.0
    date = '2014-01-05'
    #connect to database file
    dbconnect = sqlite3.connect("my.db")
    #If we want to access columns by name we need to set
    #row_factory to sqlite3.Row class
    dbconnect.row_factory = sqlite3.Row
    #now we create a cursor to work with db
    cursor = dbconnect.cursor()
    for i in range(10):
        #execute insert statement
        id += 1
        temperature += 1.1
        cursor.execute('''insert into temperature values (?, ?, ?)''',
        (id, temperature, date))
    dbconnect.commit()
    #execute simple select statement
    cursor.execute('SELECT * FROM temperature')
    #print data
    for row in cursor:
        print(row['id'],row['temp'],row['date'] )
    #close the connection

    create_table_sensor(dbconnect, cursor)

    cursor.execute('''insert into sensor values (1, "door", "kitchen")''')
    cursor.execute('''insert into sensor values (2, "temperature", "kitchen")''')
    cursor.execute('''insert into sensor values (3, "door", "garage")''')
    cursor.execute('''insert into sensor values (4, "motion", "garage")''')
    cursor.execute('''insert into sensor values (5, "temperature", "garage")''')
    dbconnect.commit()

    cursor.execute('SELECT * FROM sensor where zone == "kitchen"')

    for row in cursor:
        print(row['sensorID'],row['type'],row['zone'] )

    cursor.execute('SELECT * FROM sensor where type == "door"')

    for row in cursor:
        print(row['sensorID'],row['type'],row['zone'] )
    #close the connection

    dbconnect.close()