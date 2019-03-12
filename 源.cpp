#include<iostream>
using namespace std;
const int inf = 99999;
int shortlength(int n, int x, int y, int** array)
{
	for(int m=1;m<=n;m++)
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++)
	           if (array[i][m] + array[m][j] < array[i][j])
		             array[i][j] = array[i][m] + array[m][j];
	int temp = array[x][y];
	delete array;
	if (temp == inf)
		return 0;
	return temp;
}
int main()
{
	int n, m;
	cin >> n >> m;
	int** array = new int*[n+1];
	for (int i = 1; i <= n; i++)
		array[i] = new int[n];
	//初始化数组
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			if (i == j)
				array[i][j] = 0;
			else
			    array[i][j] = inf;
	//开始输入
	for (int i = 0; i < m; i++)
	{
		int city1, city2, length;
		cin >> city1 >> city2 >> length;
		array[city1][city2] = length;
		array[city2][city1] = length;
	}
	int x, y;
	cin >> x >> y;
	cout << shortlength(n, x, y, array);
	system("pause");
}