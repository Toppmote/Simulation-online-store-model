import kotlin.random.Random

class Shop (private val products: List<Product>, val customers: List<Customer>, val companies: List<Company>, var income: Int) {
    //Выбор заказчика (обычный покупатель или покупатель-компания
    fun chooseCustomer() {
        income += when (Random.nextBoolean()){
            true -> {
                //Выбор обычного покупателя (индекс)
                val index = (0..14).random()
                customers[index].createOrder(products) //Добавляем доход после заказа товаров
            }
            false ->{
                //Выбор покупателя компании (индекс)
                val index = (0..4).random()
                companies[index].createOrder(products)
            }
        }
    }
    //Функция поставки товара
    fun supply() {
        val count = (2..6).random()//Сколько товаров доставят в магазин
        val indexes = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled() //Первые count товаров будут добавлены в магазин
        var totalCost = 0
        println("В результате поставки товаров на склад было добавлено: ")
        for(i in 0 until count){
            val productUnits = (30..50).random()//Сколько данного товара будет добавлено
            products[indexes[i]].count += productUnits//Добавляем товар
            println("     ${i + 1}. $productUnits штук товара ${products[indexes[i]].name}")
            totalCost += productUnits * products[indexes[i]].price
        }
        //Скидка магазину от производителей 35%
        totalCost *= 65
        totalCost /= 100
        //Вычитаем из дохода стоимость купленного товара
        this.income -= totalCost
        println("Общая стоимость поставки товаров составила $totalCost")
        println()
    }
    //Функция просмотра товаров на складе
    fun storageView(){
        println()
        println("Доступные товары на складе: ")
        for(i in products.indices)
            println("     ${i + 1}. ${products[i].name} в количестве ${products[i].count}")
    }
    //Функция печати покупателей
    fun printCustomers(){
        for(i in customers.indices)
            println("     ${i + 1}. ${customers[i].name}. Количество заказов - ${customers[i].orders.size}")
        println()
    }
    //Функция печати компаний
    fun printCompanies(){
        for(i in companies.indices)
            println("     ${i + 1}. \"${companies[i].name}\". Количество заказов - ${companies[i].orders.size}")
        println()
    }
    //Функция нахождения покупателя с максимальным числом заказов
    fun customerWithMaximumOrders(): Customer? = customers.maxBy { it.orders.size }
    //Функция нахождения компании с максимальным числом заказов
    fun companyWithMaximumOrders(): Company? = companies.maxBy { it.orders.size }
    //Функция нахождения того, сколько раз заказывали заданный товар покупатели
    fun getNumberOfTimesProductWasOrderedByCustomers(product: String): Int = customers.flatMap { it.orders }. flatMap { it.products }. filter { it.name.contains(product) }.count()
    //Функция нахождения того, сколько раз заказывали заданный товар компании
    fun getNumberOfTimesProductWasOrderedByCompanies(product: String): Int = companies.flatMap { it.orders }. flatMap { it.products }. filter { it.name.contains(product) }.count()
}