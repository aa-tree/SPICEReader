#! /bin/sh
#
#

gfortran -c *.f
mv *.o lib/
cd lib
ar r lib_fortran.a *.o
rm *.o
echo 'Done!'

