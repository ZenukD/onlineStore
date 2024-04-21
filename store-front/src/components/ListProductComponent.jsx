import React, { useEffect, useState } from "react";
import { deleteProduct, getAllProducts } from "../services/ProductService.js";
import { useNavigate } from "react-router-dom";
import { getLoggedInUser, isAdminUser } from "../services/AuthService.js";
import { addOrder } from "../services/OrderService.js";

const ListProductComponent = () => {
    const [products, setProducts] = useState([]);
    const [sortBy, setSortBy] = useState({ field: null, order: "asc" });
    const [searchQuery, setSearchQuery] = useState("");

    const navigate = useNavigate();
    const isAdmin = isAdminUser();
    const username = getLoggedInUser();

    useEffect(() => {
        listProducts();
    }, []);

    function listProducts() {
        getAllProducts()
            .then((response) => {
                setProducts(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }

    function addNewProduct() {
        navigate('/add-product');
    }

    function updateProducts(id) {
        navigate(`/add-product/${id}`);
    }

    function removeProduct(id) {
        deleteProduct(id)
            .then(() => {
                listProducts();
            })
            .catch(error => {
                console.error(error);
            });
    }

    function sortProducts(field) {
        const order = sortBy.field === field && sortBy.order === "asc" ? "desc" : "asc";
        const sortedProducts = [...products].sort((a, b) => {
            if (a[field] < b[field]) return order === "asc" ? -1 : 1;
            if (a[field] > b[field]) return order === "asc" ? 1 : -1;
            return 0;
        });
        setSortBy({ field, order });
        setProducts(sortedProducts);
    }

    function addToOrder(id) {
        const order = { username, product: { id } };

        addOrder(order)
            .then((response) => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }


    const filteredProducts = products.filter(product =>
        product.name.toLowerCase().includes(searchQuery.toLowerCase())
    );

    return (
        <div className="container">
            <div className="col">
                <h2 className="text-center">Goods</h2>
                {isAdmin && (
                    <button className="btn btn-primary mb-2" onClick={addNewProduct}>
                        Add Product
                    </button>
                )}
            </div>
            <div className="row mb-3">
                <div className="col">
                    <div className="btn-group float-end">
                        <input
                            type="text"
                            className="form-control me-2"
                            placeholder="Search by name"
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                        />
                        <button
                            className="btn btn-sm btn-outline-secondary"
                            onClick={() => sortProducts("name")}
                        >
                            Name {sortBy.field === "name" && (sortBy.order === "asc" ? "▲" : "▼")}
                        </button>
                        <button
                            className="btn btn-sm btn-outline-secondary"
                            onClick={() => sortProducts("price")}
                        >
                            Price {sortBy.field === "price" && (sortBy.order === "asc" ? "▲" : "▼")}
                        </button>
                    </div>
                </div>
            </div>

            <div className="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-3">
                {filteredProducts.map(product => (
                    <div key={product.id} className="col">
                        <div className="card shadow-sm">
                            <img
                                className="bd-placeholder-img card-img-top"
                                src={`data:image/jpeg;base64,${product.image}`}
                                alt={product.name}
                            />
                            <div className="card-body">
                                <h5 className="card-title text-center">{product.name}</h5>
                                <p className="card-text text-center">{product.description}</p>
                                <p className="card-text text-center">${product.price}</p>
                                <div className="d-flex justify-content-between align-items-center">
                                    {isAdmin && (
                                        <button
                                            className="btn btn-danger"
                                            onClick={() => removeProduct(product.id)}
                                        >
                                            Delete
                                        </button>
                                    )}
                                    {isAdmin && (
                                        <button
                                            className="btn btn-primary"
                                            onClick={() => updateProducts(product.id)}
                                        >
                                            Update
                                        </button>
                                    )}
                                    <button
                                        className="btn btn-outline-success"
                                        onClick={() => addToOrder(product.id)}
                                    >
                                        <svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            width="16"
                                            height="16"
                                            fill="currentColor"
                                            className="bi bi-basket"
                                            viewBox="0 0 16 16"
                                        >
                                            <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9zM1 7v1h14V7zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5"/>
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ListProductComponent;
