import { CartItems } from "./CartItems";
import { users } from "./users";

export interface Cart {
    idPanier: number;              // Corresponds to Long idPanier in Java
    utilisateur: users;      // One-to-One relationship with Utilisateur
    items: CartItems[];           // List of PanierItem
    total: number;                 // Corresponds to Double total
  }