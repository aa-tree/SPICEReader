#! /bin/sh
#
#

gfortran -g -fbacktrace *.f  ../../code/*f -o %_TEST_ID_VALUE%.exe ../../toolkit/lib/spicelib.a
