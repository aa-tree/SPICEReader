#! /bin/sh

echo "First we'll verify if the user has downloaded all the SPICELib's components. \n"

# Check the existence of SPICE's library file in /fortran_src/toolkit/lib/.
allwell=1
file="../fortran_src/toolkit/lib/spicelib.a"
if [ -f "$file" ]
then
	echo "$file found. [OK]"
else
	echo "$file not found. [ERROR]"
    allwell=0
fi

# Check the existence of BSP files in /fortran_src/eph folder.
input="list_eph"
while IFS= read -r line
do
file="../fortran_src/eph/"$line
if [ -f "$file" ]
then
	echo "$file found. [OK]"
else
	echo "$file not found. [ERROR]"
    allwell=0
fi

done < "$input"

# Check the existence of required files in /fortran_src/gkernels folder.

input="list_gkernels"
while IFS= read -r line
do
file="../fortran_src/gkernels/"$line
if [ -f "$file" ]
then
	echo "$file found. [OK]"
else
	echo "$file not found. [ERROR]"
    allwell=0
fi


done < "$input"

if [ $allwell -eq 1 ]
then
    echo "\n All SPICELIB Components found. \n"
fi
if [ $allwell -eq 0 ]
then
    echo " \n There are some missing files. Fix them before we can move on."
    exit
fi

## Compile Java programs.
cd ../java_base/src
javac *.java

#mv *.class tmp/
#cd tmp
#jar cmf ../manifest.mf SPICEReader.jar *.class
    


