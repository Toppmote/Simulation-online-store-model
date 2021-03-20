open class Customer (val name: String, val orders: MutableList<Order>) {
    //Совершение заказа
    open fun createOrder(products: List<Product>): Int {
        val count = (1..5).random() //Сколько видов товаров купит покупатель
        val indexes = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled() //Первые count товаров будут куплены
        val listOfProducts = mutableListOf<Product>() //Список купленных товаров
        var totalCost = 0 //Общая стоимость заказа
        var realCount = 0 //Сколько товаров реально купит
        for (i in 0 until count) {
            if (products[indexes[i]].count != 0) { //Если данный товар есть на складе, если нет, то пропускаем
                realCount++
                val productUnits = (1..10).random()//Сколько данного товара будет куплено
                when (productUnits > products[indexes[i]].count) {
                    //Если покупатель хочет купить больше товара, чем есть в наличии
                    true -> {
                        //Добавляем к списку заказанных товаров
                        listOfProducts.add(
                            Product(
                                products[indexes[i]].name,
                                products[indexes[i]].price,
                                products[indexes[i]].count
                            )
                        )
                        totalCost += products[indexes[i]].price * products[indexes[i]].count //Прибавляем к стоимости заказа
                        products[indexes[i]].count = 0 //Теперь товара нет на складе
                    }
                    //Если товара на складе достаточно
                    false -> {
                        //Добавляем к списку заказанных товаров
                        listOfProducts.add(
                            Product(
                                products[indexes[i]].name,
                                products[indexes[i]].price,
                                productUnits
                            )
                        )
                        totalCost += products[indexes[i]].price * productUnits //Прибавляем к стоимости заказа
                        products[indexes[i]].count -= productUnits //Товаров на складе стало меньше
                    }
                }
            }
        }
        //Печатаем информацию о заказе (если был куплен хоть один товар)
        if (realCount != 0) {
            this.orders.add(Order(listOfProducts, totalCost)) //Добавляем в список заказов
            println("Покупатель ${this.name} заказал несколько видов товара в количестве $realCount штук: ")
            for (i in 0 until realCount)
                println("     ${i + 1}. ${listOfProducts[i].name} в количестве ${listOfProducts[i].count} штук")
            println("Общая стоимость заказа составила $totalCost")
        }
        println()
        return totalCost
    }
    //Печать заказов
    open fun printOrders() {
        println()
        if (this.orders.size != 0) {
            println("Покупатель ${this.name} совершил ${this.orders.size} заказов:")
            println()
            for (i in orders.indices) {
                println("     Заказ №${i + 1}:")
                for (j in orders[i].products.indices)
                    println("        ${j + 1}. ${orders[i].products[j].name} в количестве ${orders[i].products[j].count}")
                println("Общая сумма заказа составляет ${orders[i].cost}")
                println()
            }
        } else {
            println("Покупатель ${this.name} не совершал заказов.")
            println()
        }
    }
    //Общая сумма заказов покупателя
    fun getTotalOrdersPrice(): Int = orders.sumBy { it.cost }
}