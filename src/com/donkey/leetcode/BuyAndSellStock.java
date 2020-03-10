package com.donkey.leetcode;

/**
 * 买卖股票最佳时机
 */
public class BuyAndSellStock {
    public static void main(String[] args) {
        BuyAndSellStock stock = new BuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(stock.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (maxProfit < (prices[i] - minPrice))
                maxProfit = prices[i] - minPrice;

        }
        return maxProfit;
    }
}
