### Features
 - Handles B2B and B2C models;

### How it works?
 - B2B → Company → Buildings → ClientGroups → Residents
 - B2C → Building → ClientGroup → Residents (Company is null)
 - Every resident belongs to exactly one building, via their client group. 
 - Each building can only have one client group, avoiding conflicts.

