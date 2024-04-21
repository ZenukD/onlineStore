import './App.css'
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";
import ListProductComponent from "./components/ListProductComponent.jsx";
import AddProductComponent from "./components/AddProductComponent.jsx";
import LoginComponent from "./components/LoginComponent.jsx";
import RegisterComponent from "./components/RegisterComponent.jsx";
import {isUserLoggedIn} from "./services/AuthService.js";
import OrdersComponent from "./components/OrdersComponent.jsx";
import UpdateUserFormComponent from "./components/UpdateUserFormComponent.jsx";

function AuthenticatedRoute({children}) {
    const isAuth = isUserLoggedIn()
    if (isAuth) {
        return children
    }
    return <Navigate to="/"/>
}

function App() {

    return (
        <>
            <BrowserRouter>
                <HeaderComponent/>
                <Routes>
                    {/* http://localhost:8080 */}
                    <Route path='/' element={<ListProductComponent/>}></Route>
                    {/* http://localhost:8080/products */}
                    <Route path='/products' element={
                        <AuthenticatedRoute>
                        <ListProductComponent/>
                        </AuthenticatedRoute>
                    }></Route>

                    {/* http://localhost:8080/products/add-product */}
                    <Route path='/add-product' element={
                        <AuthenticatedRoute>
                            <AddProductComponent/>
                        </AuthenticatedRoute>
                    }></Route>
                    {/* http://localhost:8080/products/add-product/1 */}
                    <Route path='/add-product/:id' element={
                        <AuthenticatedRoute>
                            <AddProductComponent/>
                        </AuthenticatedRoute>
                    }></Route>

                    {/* http://localhost:8080/orders */}
                    <Route path='/orders' element={
                        <AuthenticatedRoute>
                            <OrdersComponent/>
                        </AuthenticatedRoute>
                    }></Route>

                    {/* http://localhost:8080/user */}
                    <Route path='/user' element={
                        <AuthenticatedRoute>
                            <UpdateUserFormComponent/>
                        </AuthenticatedRoute>
                    }></Route>

                    {/* http://localhost:8080/auth/register */}
                    <Route path='/register' element={<RegisterComponent />}></Route>
                    {/* http://localhost:8080/auth/login */}
                    <Route path='/login' element={<LoginComponent />}></Route>

                </Routes>
                <FooterComponent/>
            </BrowserRouter>
        </>
    )
}

export default App
