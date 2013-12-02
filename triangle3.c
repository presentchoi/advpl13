#include <math.h>
void triangle3 (long long a, long long b, long long c)
{ /* input must be ordered: a >= b >= c */
	long long class;
	double area;
	double as;
	double bs;
	double cs;
	double s;
	if ( (a<b) || (b<c) )
	{
		class = -1;
		area = 0; /* input is not in the waited form */
	}
	else
	{
		if ( a >= (b + c) )
		{
			class = 0; /* it is not a triangle */
			area = 0;
		}
		else
		{
			if ( (a != b) && (b != c) ) /* escaleno */
			{
				as = a*a;
				bs = b*b;
				cs = c*c;
				if (as == bs + cs) /* retangulo */
				{
					class = 3;
					area = b * c / 2.0;
				}
				else
				{
					s = (a+b+c) / 2.0;
					area = sqrt(s*(s-a)*(s-b)*(s-c));
					if ( as < bs + cs )
						class = 4; /* agudo */
					else
						class = 5; /* obtuso */
				}
			}
			else
				if ( (a == b) && (b == c) )
				{
					class = 1; /* equilatero */
					area = a*a*sqrt(3.0)/4.0;
				}
				else
				{
					class = 2; /* isoceles */
					if ( a == b )
						area = c*sqrt(4*a*b-c*c)/4;
					else
						area = a*sqrt(4*b*c-a*c)/4;
				}
		}
	}
}
