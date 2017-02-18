import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] results;

    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        this.results = new double[trials];
        double nSquare = n * n;

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                perc.open(row, col);
            }
            int openSites = perc.numberOfOpenSites();
            results[i] = openSites / nSquare;
        }
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(results);
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return (mean() - 1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return (mean() + 1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) { // test client (described below)
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.println(String.format("%-20s= %.10f", "mean", ps.mean()));
        System.out.println(String.format("%-20s= %.10f", "stddev", ps.stddev()));
        System.out.println(String.format("%-20s= [%.10f,%.10f]", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi()));
    }
}