
#!/bin/bash
# Basic while loop  mientas que
counter=1
while [ $counter -le 10 ]
do
  echo $counter
  ((counter++))
done
echo All done


counter=1
# Basic dowhilw loop    hasta que
until [ $counter -gt 10 ]
 do
  #statements
  echo $counter
  ((counter++))
done
echo All done

# for loop  iteracion de listas
names='Stan Kyle Cartman'
for name in $names
do
  echo $name
done
echo All done

# Basic range with steps for loop DECRECEINTE EN 5, {100..0}  DE COORIDO
for value in {100..0..10}
do
  echo $value
done
echo All done


# Make a php copy of any html files POR MEDIO DE WWILDCARDS
for value in $1/*.html
do
  cp $value $1/$( basename -s .html $value ).php
done



# Make a backup set of files
for value in $1/*
do
  if [ ! -r $value ]
  then
    echo $value not readable 1>&2
    continue
  fi
  cp $value $1/backup/
done


# A simple menu system
names='Kyle Cartman Stan Quit'
PS3='Select character: '
select name in $names
do
  if [ $name == 'Quit' ]
  then
  break
  fi
echo Hello $name
done
echo Bye
