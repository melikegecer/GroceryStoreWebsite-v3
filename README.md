# Grocery Store Website (Evolutionary Project Series)

This is a four-phase project series developed for a Software Architectures course at the University of Lausanne. The repositories track the evolution of a monolithic e-commerce application into a distributed, service-oriented architecture, demonstrating competence in various software design patterns and implementation technologies.

### **Phase 1: GroceryStoreWebsite-v1**
* **Repository:** [melikegecer/GroceryStoreWebsite-v1](https://github.com/melikegecer/GroceryStoreWebsite-v1)
* **Description:** The initial, simplest version of the grocery store website. This phase focused on core business logic and persistence, embodying a highly cohesive, monolithic structure.

### **Phase 2: GroceryStoreWebsite-v2**
* **Repository:** [melikegecer/GroceryStoreWebsite-v2](https://github.com/melikegecer/GroceryStoreWebsite-v2)
* **Description:** An enhancement over v1, integrating fundamental web presentation layers. This version introduced a basic user interface and styling, marking the transition into a fully functional web application.

### **Phase 3: GroceryStoreWebsite-v3**
* **Repository:** [melikegecer/GroceryStoreWebsite-v3](https://github.com/melikegecer/GroceryStoreWebsite-v3)
* **Description:** Further development and refinement of the monolithic application. This phase involved optimizing the integration between server-side logic and front-end content generation.

### **Phase 4: GroceryStoreWebsite-v4 (Client & Service Split)**
This final phase represents a major architectural shift from a monolith to a **Service-Oriented Architecture (SOA)**, separating the user interface from the backend business logic.

#### **A. GroceryStoreWebsite-v4-Client**
* **Repository:** [melikegecer/GroceryStoreWebsite-v4-Client](https://github.com/melikegecer/GroceryStoreWebsite-v4-Client)
* **Description:** The dedicated client-side application responsible for the user interface. It communicates with the separate backend service (v4-Service) to retrieve and manipulate data.

#### **B. GroceryStoreWebsite-v4-Service**
* **Repository:** [melikegecer/GroceryStoreWebsite-v4-Service](https://github.com/melikegecer/GroceryStoreWebsite-v4-Service)
* **Description:** The backend service component that handles all core business logic, data storage, and transactional operations. It exposes APIs for the client to consume, fully decoupling the application tiers.
