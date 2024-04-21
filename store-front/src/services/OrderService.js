import axios from "axios";

const BASE_ORDER_REST_API_URL = 'http://localhost:8080/api/orders'

export const addOrder = (order) => axios.post(BASE_ORDER_REST_API_URL, order)

export const findOrderByUsername = (username) => axios.get(BASE_ORDER_REST_API_URL + '/' + username + '/findByUsername')

export const deleteByUsername = (username) => axios.delete(BASE_ORDER_REST_API_URL + '/'+ username + '/deleteByUsername')

export const increaseQuantity = (id) => axios.patch(BASE_ORDER_REST_API_URL + '/' + id + '/increase')
export const reduceQuantity = (id) => axios.patch(BASE_ORDER_REST_API_URL + '/' + id + '/reduce')

export const deleteOrder = (id) => axios.delete(BASE_ORDER_REST_API_URL + '/' + id)