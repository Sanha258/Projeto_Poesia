import React, { useState } from 'react';
import axios from 'axios';

const LoginAndTest = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useState('');
  const [responseData, setResponseData] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async () => {
    try {
      const res = await axios.post('/api/login', { email, password });
      setToken(res.data.token);
      setError('');
      alert('Login bem-sucedido! Token salvo.');
    } catch (err) {
      setError('Erro ao fazer login: ' + err.response?.data?.message || err.message);
    }
  };

  const testProtectedRoute = async () => {
    try {
      const res = await axios.get('/api/protected', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setResponseData(JSON.stringify(res.data, null, 2));
      setError('');
    } catch (err) {
      setError('Erro ao acessar rota protegida: ' + err.response?.data?.message || err.message);
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Login</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      /><br /><br />
      <input
        type="password"
        placeholder="Senha"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      /><br /><br />
      <button onClick={handleLogin}>Fazer Login</button>

      <h2>Testar Rota Protegida</h2>
      <button onClick={testProtectedRoute} disabled={!token}>
        Testar com Token
      </button>

      {responseData && (
        <pre style={{ background: '#eee', padding: 10 }}>{responseData}</pre>
      )}
      {error && (
        <p style={{ color: 'red' }}>{error}</p>
      )}
    </div>
  );
};

export default LoginAndTest;
