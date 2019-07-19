#! /bin/sh
#
#

gfortran *.f ../../code/*.f  -o test_program.exe ../../toolkit/lib/spicelib.a
