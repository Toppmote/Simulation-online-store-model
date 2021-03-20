class Company(name: String, orders: MutableList<Order>) : Customer (name, orders) {
    //Совершение заказа
    override fun createOrder(products: List<Product>): Int {
        val count = (2..4).random() //Сколько видов товаров хочет купить компания
        var realCount = 0 //Сколько товаров реально купит компания
        val indexes = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled() //Первые count товаров будут куплены
        val listOfProducts = mutableListOf<Product>() //Список купленных товаров
        var totalCost = 0 //Общая стоимость заказа
        for (i in 0 until count) {
            if (products[indexes[i]].count != 0) { //Если данный товар есть на складе, если нет, то пропускаем
                realCount++
                val productUnits = (20..40).random()//Сколько данного товара будет куплено
                when (productUnits > products[indexes[i]].count) {
                    //Если компания хочет купить больше товара, чем есть в наличии
                    true -> {
                        //Добавляем к списку заказанных товаров
                        listOfProducts.add(
                            Product(
                                products[indexes[i]].name,
                                products[indexes[i]].price,
                                products[indexes[i]].count
                            )
                        )
                        products[indexes[i]].count = 0 //Теперь товара нет на складе
                        totalCost += products[indexes[i]].price * products[indexes[i]].count //Прибавляем к стоимости заказа
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
                        products[indexes[i]].count -= productUnits //Товаров на складе стало меньше
                        totalCost += products[indexes[i]].price * productUnits //Прибавляем к стоимости заказа
                    }
                }
            }
        }
        //Вычилсяем скидку
        totalCost *= 80
        totalCost /= 100
        //Печатаем информацию о заказе (если был куплен хоть один товар)
        if (realCount != 0) {
            orders.add(Order(listOfProducts, totalCost)) //Добавляем в список заказов
            println("Компания \"${this.name}\" заказала несколько видов товара в количестве $realCount штук: ")
            for (i in 0 until realCount)
                println("     ${i + 1}. ${listOfProducts[i].name} в количестве ${listOfProducts[i].count} штук")
            println("Общая стоимость заказа с учётом 20% скидки составила $totalCost")
        }
        println()
        return totalCost
    }

    //Печать заказов
    override fun printOrders() {
        println()
        if (this.orders.size != 0) {
            println("Компания \"${this.name}\" совершила ${this.orders.size} заказов:")
            println()
            for (i in orders.indices) {
                println("     Заказ №${i + 1}:")
                for (j in orders[i].products.indices)
                    println("        ${j + 1}. ${orders[i].products[j].name} в количестве ${orders[i].products[j].count}")
                println("Общая сумма заказа составляет ${orders[i].cost}")
                println()
            }
        } else {
            println("Компания ${this.name} не совершала заказов.")
            println()
        }
    }
}