fun main() {
    //Имена покупателей
    val namesOfCustomers = arrayOf<String>(
        "Татьяна", "Павел", "Виталий", "Максим", "Глеб", "Елена",
        "Екатерина", "Дмитрий", "Никита", "Наталья", "Вячеслав", "Геннадий", "Михаил", "Степан", "Александр"
    )
    //Названия компаний
    val namesOfCompanies = arrayOf<String>("Магнит", "Аникс", "Мария Ра", "Новэкс", "Гарант")
    //Названия и цены товаров
    val namesOfProducts = arrayOf<String>("шоколад", "батарейки", "шампунь", "лимонад", "корм для кошек",
        "йогурт", "порошок", "тарелка", "зубная щётка", "кружка", "футболка")
    val propertiesOfProducts = arrayOf<Pair<String, Int>>(
        "шоколад" to 50,
        "батарейки" to 80,
        "шампунь" to 140,
        "лимонад" to 55,
        "корм для кошек" to 90,
        "йогурт" to 35,
        "порошок" to 105,
        "тарелка" to 30,
        "зубная щётка" to 150,
        "кружка" to 70,
        "футболка" to 250
    )
    //Список покупателей
    val customers = mutableListOf<Customer>()
    for (i in 0 until 15) {
        customers.add(Customer(namesOfCustomers[i], mutableListOf<Order>()))
    }
    val companies = mutableListOf<Company>()
    //Список компаний
    for (i in 0 until 5) {
        companies.add(Company(namesOfCompanies[i], mutableListOf<Order>()))
    }
    //Список товаров
    val products = mutableListOf<Product>()
    for (i in 0 until 10) {
        products.add(Product(propertiesOfProducts[i].first, propertiesOfProducts[i].second, 50))
    }
    val shop = Shop(products, customers, companies, 0) //Объект магазина
    //Сам цикл событий и ввод команд
    println("НАЧАЛО РАБОТЫ МОДЕЛИ")
    var terminate: Boolean = false
    do {
        val ordersCount = (3..6).random()
        println()
        println("НОВЫЕ ВЫПОЛНЕННЫЕ ЗАКАЗЫ:")
        println()
        for (i in 0 until ordersCount) {
            shop.chooseCustomer() //Создание заказа
        }
        shop.supply() //Поставка товаров в магазин
        var format = true
        var command: String? = ""
        do {
            //Ввод команд
            var next: Boolean = false
            if (format) {
                print("Введите команду: ")
                command = readLine()
            }
            format = true
            when (command) {
                "terminate" -> terminate = true
                "continue" -> next = true
                "current income" -> {
                    println()
                    println("Текущий доход магазина: ${shop.income} у. е.")
                    println()
                }
                "storage" -> {
                    shop.storageView()
                    println()
                }
                "customer orders" -> {
                    println()
                    println("Список покупателей: ")
                    shop.printCustomers()
                    print("Введите номер покупателя: ")
                    val number = numberIn(1..15)
                    shop.customers[number].printOrders()
                }
                "company orders" -> {
                    println()
                    println("Список компаний: ")
                    shop.printCompanies()
                    print("Введите номер компании: ")
                    val number = numberIn(1..5)
                    shop.companies[number].printOrders()
                }
                "highest orders count" -> {
                    val bestCustomer = shop.customerWithMaximumOrders()
                    val bestCompany = shop.companyWithMaximumOrders()
                    println()
                    if(bestCompany!!.orders.size > bestCustomer!!.orders.size)
                        println("Больше всего заказов у компании ${bestCompany.name} - ${bestCompany.orders.size}")
                    else  println("Больше всего заказов у покупателя ${bestCustomer.name} - ${bestCustomer.orders.size}")
                    println()
                }
                "total orders price of customer" -> {
                    println()
                    println("Список покупателей: ")
                    shop.printCustomers()
                    print("Введите номер покупателя: ")
                    val number = numberIn(1..15)
                    val totalSum = shop.customers[number].getTotalOrdersPrice()
                    println()
                    if (totalSum != 0)
                        println("Сумма заказов покупателя ${shop.customers[number].name} составляет $totalSum.")
                    else println("Покупатель ${shop.customers[number].name} не совершал заказов. ")
                    println()
                }
                "total orders price of company" -> {
                    println()
                    println("Список компаний: ")
                    shop.printCompanies()
                    print("Введите номер компании: ")
                    val number = numberIn(1..5)
                    val totalSum = shop.companies[number].getTotalOrdersPrice()
                    println()
                    if (totalSum != 0)
                        println("Сумма заказов компании \"${shop.companies[number].name}\" составляет $totalSum")
                    else println("Компания \"${shop.companies[number].name}\" не совершала заказов.")
                    println()
                }
                "how many times have ordered" -> {
                    println()
                    println("Возможные виды продуктов:")
                    for(s in namesOfProducts)
                        println(s)
                    println()
                    print("Введите название продукта: ")
                    var name: String?
                    do {
                        var f = false
                        name = readLine()
                        if(!namesOfProducts.contains(name)){
                            f = true
                            print("Данного товара нет. Повторите ввод: ")
                        }
                    }while(f)
                    val inCustomers = shop.getNumberOfTimesProductWasOrderedByCustomers(name.toString())
                    val inCompanies = shop.getNumberOfTimesProductWasOrderedByCompanies(name.toString())
                    val totalCount = inCompanies + inCustomers
                    println()
                    if(totalCount != 0)
                    println("Товар $name был заказан $totalCount раз.")
                    else println("Товар $name ни разу не заказывали.")
                    println()
                }
                "help" -> {
                    println()
                    println("\"terminate\" - завершение работы модели")
                    println("\"continue\" - продолжить работу модели")
                    println("\"current income\" - посмотреть текущий доход магазина")
                    println("\"storage\" - посмотреть товары на складе")
                    println("\"customer orders\" - посмотреть все заказы данного покупателя")
                    println("\"company orders\" - посмотреть все заказы данной компании")
                    println("\"highest orders count\" - посмотреть, у кого больше всего заказов")
                    println("\"total orders price of customer\" - посмотреть общую сумму заказов покупателя")
                    println("\"total orders price of company\" - посмотреть общую сумму заказов компании")
                    println("\"how many times have ordered\" - посмотреть, сколько раз товар встречается в заказах")
                    println()
                }
                //Если введена несуществующая команда
                else -> {
                    print("Неизвествая команда (для помощи введите команду help). Повторите ввод: ")
                    format = false
                    command = readLine()
                }
            }
        } while (!next && !terminate)
    } while (!terminate)
    //Вывод дохода магазина
    println("Во время работы модели доход магазина составил ${shop.income} у. е.")
    println()
    print("КОНЕЦ РАБОТЫ МОДЕЛИ")
}

//Функция проверки, является ли строка числом
fun isNumeric(s: String?): Boolean {
    return try {
        Integer.parseInt(s)
        true
    } catch (e: NumberFormatException) {
        false
    }
}
//Функция ввода индекса
fun numberIn(interval: IntRange): Int{
    var number = readLine()
    do{
        var f = false
        if(isNumeric(number)) {
            if (number!!.toInt() !in interval) {
                f = true
                print("Несуществующий номер! Повтрорите ввод: ")
                number = readLine()
            }
        }
        else {
            f = true
            print("Неверный формат! Повтрорите ввод: ")
            number = readLine()
        }
    }while(f)
    return number!!.toInt() - 1
}

class Main {}