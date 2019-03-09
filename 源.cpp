#include<iostream>
using namespace std;
int Fib( int A,int B, int n)
{
   int* f = new int[n + 1];
	f[1] = 1, f[2] = 1;
	for (int i = 3; i <= n; i++)
		f[i] = (A*f[i - 1] + B*f[i - 2]) % 7;
   int r = f[n];
	delete f;
	return r;
}
int main()
{
	int A, B;
	long long int n;
	cin >> A >> B >> n;
	int temp = n % 49;
		if (A == 0 && B == 0 && n == 0)
			return 0;
		cout << Fib(A, B, temp);
	
	system("pause");
}