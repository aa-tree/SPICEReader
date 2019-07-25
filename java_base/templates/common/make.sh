#! /bin/sh
#
#

gfortran *.f  ../../code/*f -o %_TEST_ID_VALUE%.exe ../../toolkit/lib/spicelib.a
