### Features
 - Handles B2B and B2C models;

### How it works?
 - B2B → Company → Buildings → ClientGroups → Residents
 - B2C → Building → ClientGroup → Residents (Company is null)
 - Every resident belongs to exactly one building, via their client group. 
 - Each building can only have one client group, avoiding conflicts.

### How it is structured?

src/main/java/pt/pedrorocha/mybuilding
│
├── config/          # Configuration classes
├── controller/      # HTTP controllers
├── dto/             # Data Transfer Objects
├── entity/          # JPA entities
├── exceptions/      # Custom exceptions & handlers
├── mapper/          # Mapper/conversion classes
├── model/           # Domain models
├── repository/      # Data access interfaces
└── services/        # Business logic/services
