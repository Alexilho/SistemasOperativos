#!/bin/bash
    # A simple copy script


echo "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
echo "Bienvanido " $USER " estas corriendo el script" $0
echo "Hola, me encuentro en " $PWD
echo "He recibido" $# "argumentos"
echo "He recibido mis siguientes argumentos  " $#
echo "El tiempo de ejecucion a sido de " $SECONDS
echo "Hay un problema en la linea de consola 11, di esta orden en la linea = " $LINENO


    # $0 - The name of the Bash script.
    # $1 - $9 - The first 9 arguments to the Bash script. (As mentioned above.)
    # $# - How many arguments were passed to the Bash script.
    # $@ - All the arguments supplied to the Bash script.
    # $? - The exit status of the most recently run process.
    # $$ - The process ID of the current script.
    # $USER - The username of the user running the script.
    # $HOSTNAME - The hostname of the machine the script is running on.
    # $SECONDS - The number of seconds since the script was started.
    # $RANDOM - Returns a different random number each time is it referred to.
    # $LINENO - Returns the current line number in the Bash script.



touch $1

# voy a recibir dos argumentos para copiar archvos
cp $1 $2
  # Let's verify the copy worked
echo Details for $2
ls -lh $2
