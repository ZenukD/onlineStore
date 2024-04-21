import React, {useCallback, useEffect, useState} from "react";
import {getLoggedInUser} from "../services/AuthService.js";
import {deleteOrder, findOrderByUsername, increaseQuantity, reduceQuantity} from "../services/OrderService.js";

const OrdersComponent = () => {
    const [orders, setOrders] = useState([])

    const username = getLoggedInUser()

    const listOrders = useCallback(() => {
        findOrderByUsername(username)
            .then((response) => {
                console.log(response.data)
                setOrders(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, [username]);

    useEffect(() => {
        listOrders();
    }, [listOrders]);


    function increaseOrderQuantity(id) {
        increaseQuantity(id).then((response) => {
            listOrders()
        }).catch(error => {
            console.error(error)
        })

    }

    function reduceOrderQuantity(id) {
        console.log(id)
        reduceQuantity(id).then((response) => {
            listOrders()
        }).catch(error => {
            console.error(error)
        })

    }

    function removeProduct(id) {
        deleteOrder(id).then((response) => {
            listOrders()
        }).catch(error => {
            console.error(error)
        })

    }

    function calculateTotal() {
        let total = 0;
        orders.forEach(order => {
            if (order.product && order.product.price) {
                total += order.product.price * order.quantity;
            }
        });
        return total;
    }

    return (
        <div className='container'>
            <h2 className='text-center'>Order List</h2>

            <div>
                <table className='table table-bordered table-striped'>
                    <thead>
                    <tr>
                        <th>Photo</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                        <th>Total sum</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        orders.map(order =>
                            <tr key={order.id}>
                                <td key={`${order.id}-photo`}
                                    style={{textAlign: 'center'}}>
                                    {order.product && order.product.image ? (
                                        <img
                                            className="bd-placeholder-img card-img-top"
                                            src={`data:image/jpeg;base64,${order.product.image}`}
                                            alt={order.product.name}
                                            style={{
                                                maxWidth: '70px',
                                                maxHeight: '70px',
                                                display: 'inline-block'
                                            }}
                                        />
                                    ) : null}
                                </td>
                                <td key={`${order.id}-name`}>{order.product ? order.product.name : null}</td>
                                <td key={`${order.id}-price`}>${order.product ? order.product.price : null}</td>
                                <td key={`${order.id}-quantity`}>{order.quantity}</td>
                                <td>
                                    <button className='btn btn-info'
                                            onClick={() => increaseOrderQuantity(order.id)}>+
                                    </button>

                                    <button className='btn btn-success'
                                            onClick={() => reduceOrderQuantity(order.id)}
                                            style={{marginLeft: "10px"}}>-
                                    </button>


                                    <button className='btn btn-danger' onClick={() => removeProduct(order.id)}
                                            style={{marginLeft: "10px"}}>Delete
                                    </button>
                                </td>
                                <td>${order.product && order.product.price ? order.product.price * order.quantity : null}</td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
            <div>
                <p>Total sum of all product: ${calculateTotal()}</p>
            </div>
            <button className='btn btn-success'>Order</button>
        </div>
    )
}
export default OrdersComponent