CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
C
C     Source: http://people.sc.fsu.edu/~jburkardt%20/f_src/rk4/rk4.f90
C
C     Reformatted to F77 standard.
C
CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


        subroutine rk4vec_ENCKE ( t0, m, u0, RHO0, dt, f, u )

!******************************************************************************
 !
 !! RK4VEC takes one Runge-Kutta step for a vector ODE.
 !
 !  Discussion:
 !
 !    Thanks  to Dante Bolatti for correcting the final function call to:
 !      call f ( t3, m, u3, f3 )
 !    18 August 2016.
 !
 !  Licensing:
 !
 !    This code is distributed under the GNU LGPL license.
 !
 !  Modified:
 !
 !    18 August 2016
 !
 !  Author:
 !
 !    John Burkardt
 !
 !  Parameters:
 !
 !    Input, real ( kind = 8 ) T0, the current time.
 !
 !    Input, integer ( kind = 4 ) M, the dimension of the system.
 !
 !    Input, real ( kind = 8 ) U0(M), the solution estimate at the current time.
 !
 !    Input, real ( kind = 8 ) DT, the time step.
 !
 !    Input, external F, a subroutine of the form
 !      subroutine f ( t, m, u, uprime )
 !    which evaluates the derivative UPRIME(1:M) given the time T and
 !    solution vector U(1:M).
 !
 !    Output, real ( kind = 8 ) U(M), the fourth-order Runge-Kutta solution
 !    estimate at time T0+DT.
 !
         implicit none

         integer ( kind = 4 ) m

         real ( kind = 8 ) dt
         external f
         real ( kind = 8 ) f0(m)
         real ( kind = 8 ) f1(m)
         real ( kind = 8 ) f2(m)
         real ( kind = 8 ) f3(m)
         real ( kind = 8 ) t0
         real ( kind = 8 ) t1
         real ( kind = 8 ) t2
         real ( kind = 8 ) t3
         real ( kind = 8 ) u(m)
         REAL*8 RHO0(m)
         real ( kind = 8 ) u0(m)
         real ( kind = 8 ) u1(m)
         real ( kind = 8 ) u2(m)
         real ( kind = 8 ) u3(m)
 !
 !  Get four sample values of the derivative.
 !
         call f ( t0, m, u0, RHO0, f0 )

         t1 = t0 + dt / 2.0D+00
         u1(1:m) = u0(1:m) + dt * f0(1:m) / 2.0D+00
         call f ( t1, m, u1, RHO0, f1 )

         t2 = t0 + dt / 2.0D+00
         u2(1:m) = u0(1:m) + dt * f1(1:m) / 2.0D+00
         call f ( t2, m, u2, RHO0, f2 )

         t3 = t0 + dt
         u3(1:m) = u0(1:m) + dt * f2(1:m)
         call f ( t3, m, u3, RHO0, f3 )
 !
 !  Combine them to estimate the solution U at time T1.
 !
         u(1:m) = u0(1:m) + ( dt / 6.0D+00 ) * (
     &            f0(1:m)
     &+ 2.0D+00 * f1(1:m)
     &+ 2.0D+00 * f2(1:m)
     &+           f3(1:m) )

         return
         end
