// order.model.ts
export interface Order {
  productId: number;
  productName: string;
  productPrice: number;
  productCategory: string;
  productDescription: string;
  orderQty: number;
  orderStatus: string;
  orderDate: string;
  arrivalDate: string;
  orderAddress: string;
}
