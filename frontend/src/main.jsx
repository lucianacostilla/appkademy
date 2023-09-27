import './scss/bootstrap.scss'
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.scss'
import { AuthProvider } from './Context/AuthContext'


ReactDOM.createRoot(document.getElementById('root')).render(
  <AuthProvider>
      <React.StrictMode>
        <App />
      </React.StrictMode>
  </AuthProvider>
)
