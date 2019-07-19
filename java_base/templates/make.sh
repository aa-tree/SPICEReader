#! /bin/sh
#
#

gfortran *.f  -o %_TEST_ID_VALUE%.exe ../../code/lib/fortranlib.tar ../../toolkit/lib/spicelib.a
