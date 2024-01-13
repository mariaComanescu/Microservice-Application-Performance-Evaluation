db = db.getSiblingDB('DB_test');
db.createCollection("users");
db.users.insert( { _id: 1, firstName: "Theo" , lastName:"James"} );
db.users.insert( { _id: 2, firstName: "Michael" , lastName:"Leibniz"} );
