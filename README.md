##Simulation model of an online store
**The model simulates the process of an online store. **
The store has 15 regular customers and 5 company customers. Major events (placing orders, delivery of goods) occur sequentially (for example, 5 orders and 1 delivery of goods). The quantity of delivered goods, the quantity of goods in the order, and who made the order are determined randomly. If an order is received from a customer-company, then they are given a 20% discount. Ordinary shoppers buy from 1 to 5 items in quantities of 1 to 10 each. Company buyers buy from 1 to 3 items in the amount of 10 to 50 pieces each. With each order, the online store receives a profit equal to the number of goods in the order, multiplied by its cost (if the order is from a customer-company, then a 20% discount will also be taken into account). When goods are delivered to the warehouse, from 2 to 7 types of goods will be added to the store in the amount of 40 to 80 pieces each. There are 10 types of goods in the store. With each delivery, the store pays for the goods an amount equal to the quantity of the goods multiplied by its value. All events will be displayed as a line to the console. The program can be interrupted by pressing the key, after pressing the number of profit (loss) of the store will be displayed. The entire sequence of events will be output as a message to the console.

------------
At the end of each sequence of events, the current status of the stores income (loss) is displayed, and the user can also use the list of available functions by entering the appropriate command.

**List of available commands:**
1. "terminate" - termination of the model;
2. "continue" - continue the model;
3. "current income" - see the current income of the store;
4. "storage" - view the goods in the warehouse;
5. "customer orders" - view all orders of a given customer;
6. "company orders" - view all orders of this company;
7. "highest orders count" - see who has the most orders;
8. "total orders price of customer" - see the total amount of the buyer's orders;
9. "total orders price of company" - see the total amount of orders of the company;
10. "how many times have ordered" - see how many times the product appears in orders;
11. "help" - information about all available commands.