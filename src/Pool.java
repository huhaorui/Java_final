class Pool {
    private int prizeOne, prizeTwo, prizeThree, unPrized;

    Pool(int prizeOne, int prizeTwo, int prizeThree, int total) {
        this.prizeOne = prizeOne;
        this.prizeTwo = prizeTwo;
        this.prizeThree = prizeThree;
        this.unPrized = total - prizeOne - prizeTwo - prizeThree;
    }

    int getPrize() {
        int num = prizeOne + prizeTwo + prizeThree + unPrized;
        int tmp = (int) (Math.random() * num + 1);
        if (tmp <= prizeOne) {
            prizeOne--;
            return 1;
        } else if (tmp <= prizeOne + prizeTwo) {
            prizeTwo--;
            return 2;
        } else if (tmp <= prizeOne + prizeTwo + prizeThree) {
            prizeThree--;
            return 3;
        } else {
            unPrized--;
            return 0;
        }
    }
}
