
CREATE TABLE IF NOT EXISTS User
(
    username VARCHAR(50) UNIQUE NOT NULL,
    passwordHash VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    emailAddress VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    gender enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
    address VARCHAR(255) NOT NULL,
    lastLoginTime TIMESTAMP DEFAULT NULL,
    role enum('Admin','Client','Employee', 'Agent') NOT NULL,
    token VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS Admin
(
    admin_id INT PRIMARY KEY,
    admin_name VARCHAR(50) NOT NULL,
    admin_email VARCHAR(50) NOT NULL,
    contactNo VARCHAR(50) NOT NULL,
    username VARCHAR(100) NOT NULL,
    FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Employee
(
    employeeId VARCHAR(20) PRIMARY KEY,
    joinDate DATE NOT NULL,
    endDate DATE,
    firstName VARCHAR(50) NOT NULL,
    middleName VARCHAR(50) ,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    employeeAddress VARCHAR(50) NOT NULL,
    admin_id INT ,
    username VARCHAR(100) NOT NULL,
    FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(admin_id) REFERENCES Admin(admin_id) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS UserPhoneNumber
(
    username VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,
    PRIMARY KEY (username, phoneNumber)
);



CREATE TABLE IF NOT EXISTS Agent
(
    agentId INT,
    licenceNo VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    middleName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    houseNo INT NOT NULL,
    landmark VARCHAR(50),
    city VARCHAR(50),
    Commision INT,
    employeeId VARCHAR(50),
    admin_id INT,
    username VARCHAR(100) NOT NULL,
    FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(employeeId) REFERENCES Employee(employeeId) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY(admin_id) REFERENCES Admin(admin_id) ON DELETE SET NULl ON UPDATE CASCADE,
    PRIMARY KEY(agentId)

);

CREATE TABLE IF NOT EXISTS Client
(
    clientNo INT,
    clientEmail VARCHAR(50) NOT NULL,
    clientContact VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    MiddleName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    HouseNo INT NOT NULL,
    landMark VARCHAR(54) NOT NULL,
    city VARCHAR(50) NOT NULL,
    employeeId VARCHAR(50) ,
    username VARCHAR(100) ,
    FOREIGN KEY(username) REFERENCES User(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(employeeId) REFERENCES Employee(employeeId) ON DELETE SET NULL ON UPDATE CASCADE,
    PRIMARY KEY(clientNo)
);

CREATE TABLE  IF NOT EXISTS Insurance
(
    InsuranceId VARCHAR(50),
    InsuranceType VARCHAR(50) NOT NULL,
    InsuranceName VARCHAR(50) NOT NULL,
    PRIMARY KEY(InsuranceId)
);

CREATE TABLE IF NOT EXISTS Policies
(
    PolicyTerm INT NOT NULL,
    TotalAmount INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    InsuranceId VARCHAR(50),
    FOREIGN KEY (InsuranceId) REFERENCES Insurance(InsuranceId) ON DELETE CASCADE,
    PRIMARY KEY(PolicyTerm,InsuranceId)
);

CREATE TABLE IF NOT EXISTS Sells
(
    agentId INT,
    clientNo INT ,
    InsuranceId VARCHAR(50),
    PolicyTerm INT ,
    buyDate DATE NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY (InsuranceId,PolicyTerm) REFERENCES Policies(InsuranceId,PolicyTerm) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(agentId) REFERENCES Agent(agentId) ON DELETE cascade ON UPDATE CASCADE,
    FOREIGN KEY(clientNo) REFERENCES Client(clientNo) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(InsuranceId, PolicyTerm, clientNo, agentId)
);