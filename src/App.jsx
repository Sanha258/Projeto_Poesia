import { useState } from 'react'
import './App.css'
import LoginAndTest from './component/LoginAndTest'

function App() {
  const [count, setCount] = useState(0)

  return (
      <div>
        <LoginAndTest/> 
      </div>      
  )
}

export default App
