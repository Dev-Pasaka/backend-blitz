package presentation.routes.pages

import kotlinx.html.*
import kotlinx.html.stream.createHTML

fun HTML.blitzApp() {
    head {
        title("Kotlin-Blitz - AI Platform")
        meta {
            attributes["name"] = "viewport"
            attributes["content"] = "width=device-width, initial-scale=1.0"
        }
        link(rel = "stylesheet", href = "https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css")
        style {
            unsafe {
                +"""
                body {
                    background-color: #0f172a;
                    color: #ffffff;
                    min-height: 100vh;
                }
                .loading-screen {
                    opacity: 1;
                    transition: opacity 0.3s ease-out;
                }
                .loading-screen.hidden {
                    opacity: 0;
                    pointer-events: none;
                }
                .page-content {
                    display: none;
                    opacity: 0;
                    transform: translateY(10px);
                    transition: all 0.3s ease-out;
                }
                .page-content.active {
                    display: flex;
                    opacity: 1;
                    transform: translateY(0);
                }
                .loading-dot {
                    animation: bounce 1.4s infinite;
                }
                @keyframes bounce {
                    0%, 100% { transform: translateY(0); }
                    50% { transform: translateY(-10px); }
                }
                .password-container {
                    position: relative;
                }
                .toggle-password {
                    position: absolute;
                    right: 12px;
                    top: 50%;
                    transform: translateY(-50%);
                    cursor: pointer;
                    color: #94a3b8;
                }
                .toggle-password:hover {
                    color: #e2e8f0;
                }
                @media (max-width: 768px) {
                    .dashboard-grid {
                        grid-template-columns: 1fr;
                    }
                    .chat-column {
                        order: 2;
                    }
                    .history-column {
                        order: 1;
                    }
                    .model-column {
                        order: 3;
                    }
                }
                """
            }
        }
    }

    body {
        // Loading Screen
        div {
            id = "loading-screen"
            attributes["class"] = "fixed inset-0 flex items-center justify-center bg-slate-900 loading-screen"
            div {
                attributes["class"] = "flex space-x-3"
                (1..3).forEach { i ->
                    div {
                        attributes["class"] = "w-3 h-3 bg-cyan-400 rounded-full loading-dot"
                        attributes["style"] = "animation-delay: ${0.32 * i}s"
                    }
                }
            }
        }

        // Auth Container
        div {
            id = "auth-container"
            attributes["class"] = "page-content fixed inset-0 items-center justify-center bg-slate-900/90 backdrop-blur-sm p-4"
            div {
                attributes["class"] = "w-full max-w-md bg-slate-800 rounded-xl shadow-xl overflow-hidden"

                // Tabs
                div {
                    attributes["class"] = "flex border-b border-slate-700"
                    button {
                        id = "login-tab"
                        attributes["class"] = "flex-1 py-4 font-medium text-center bg-slate-800 text-cyan-400"
                        attributes["onclick"] = "switchTab('login')"
                        +"Sign In"
                    }
                    button {
                        id = "signup-tab"
                        attributes["class"] = "flex-1 py-4 font-medium text-center bg-slate-700 text-slate-400"
                        attributes["onclick"] = "switchTab('signup')"
                        +"Sign Up"
                    }
                }

                // Login Form
                // Replace the existing login form div with this corrected version
                div {
                    id = "login-form-container"
                    attributes["class"] = "p-6"
                    form {
                        id = "login-form"
                        attributes["class"] = "space-y-5"
                        attributes["onsubmit"] = "event.preventDefault(); handleAuth('login');"

                        div {
                            label {
                                attributes["class"] = "block text-sm font-medium mb-1 text-slate-200"
                                attributes["for"] = "login-email"
                                +"Email Address"
                            }
                            input {
                                id = "login-email"
                                type = InputType.email
                                attributes["class"] = "w-full p-3 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500 text-white"
                                attributes["required"] = "true"
                                placeholder = "your@email.com"
                            }
                        }

                        div {
                            attributes["class"] = "password-container relative"
                            label {
                                attributes["class"] = "block text-sm font-medium mb-1 text-slate-200"
                                attributes["for"] = "login-password"
                                +"Password"
                            }
                            input {
                                id = "login-password"
                                type = InputType.password
                                attributes["class"] = "w-full p-3 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500 text-white pr-12 border border-slate-600"
                                attributes["required"] = "true"
                                attributes["autocomplete"] = "current-password"
                                placeholder = "••••••••"
                            }
                            span {
                                id = "toggle-login-password"
                                attributes["class"] = "toggle-password absolute right-3 top-1/2 transform translate-y-1/4 text-slate-400 hover:text-slate-200 cursor-pointer"
                                attributes["onclick"] = "togglePasswordVisibility('login-password', this)"
                                +"👁️"
                            }
                        }

                        button {
                            type = ButtonType.submit
                            attributes["class"] = "w-full p-3 bg-cyan-600 text-white rounded-lg hover:bg-cyan-500 transition-colors font-medium"
                            +"Sign In"
                        }
                    }
                }

                // Signup Form
                div {
                    id = "signup-form-container"
                    attributes["class"] = "hidden p-6"
                    form {
                        id = "signup-form"
                        attributes["class"] = "space-y-5"
                        attributes["onsubmit"] = "event.preventDefault(); handleAuth('signup');"

                        div {
                            attributes["class"] = "grid grid-cols-2 gap-4"
                            div {
                                label {
                                    attributes["class"] = "block text-sm font-medium mb-1"
                                    attributes["for"] = "signup-firstName"
                                    +"First Name"
                                }
                                input {
                                    id = "signup-firstName"
                                    type = InputType.text
                                    attributes["class"] = "w-full p-3 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500"
                                    attributes["required"] = "true"
                                    placeholder = "John"
                                }
                            }
                            div {
                                label {
                                    attributes["class"] = "block text-sm font-medium mb-1"
                                    attributes["for"] = "signup-lastName"
                                    +"Last Name"
                                }
                                input {
                                    id = "signup-lastName"
                                    type = InputType.text
                                    attributes["class"] = "w-full p-3 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500"
                                    attributes["required"] = "true"
                                    placeholder = "Doe"
                                }
                            }
                        }

                        div {
                            label {
                                attributes["class"] = "block text-sm font-medium mb-1"
                                attributes["for"] = "signup-email"
                                +"Email Address"
                            }
                            input {
                                id = "signup-email"
                                type = InputType.email
                                attributes["class"] = "w-full p-3 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500"
                                attributes["required"] = "true"
                                placeholder = "your@email.com"
                            }
                        }

                        div {
                            attributes["class"] = "password-container"
                            label {
                                attributes["class"] = "block text-sm font-medium mb-1"
                                attributes["for"] = "signup-password"
                                +"Password"
                            }
                            input {
                                id = "signup-password"
                                type = InputType.password
                                attributes["class"] = "w-full p-3 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500 pr-10"
                                attributes["required"] = "true"
                                placeholder = "••••••••"
                            }
                            span {
                                id = "toggle-signup-password"
                                attributes["class"] = "toggle-password"
                                attributes["onclick"] = "togglePasswordVisibility('signup-password', this)"
                                +"👁️"
                            }
                        }

                        button {
                            type = ButtonType.submit
                            attributes["class"] = "w-full p-3 bg-cyan-600 text-white rounded-lg hover:bg-cyan-500 transition-colors font-medium"
                            +"Create Account"
                        }
                    }
                }

                div {
                    id = "auth-response"
                    attributes["class"] = "px-6 pb-6 text-center text-red-400"
                }
            }
        }

        // Dashboard
        div {
            id = "dashboard"
            attributes["class"] = "page-content h-screen flex flex-col"

            // Header
            header {
                attributes["class"] = "bg-slate-800 p-4 flex justify-between items-center border-b border-slate-700"
                h1 {
                    attributes["class"] = "text-xl font-bold text-cyan-400"
                    +"Kotlin-Blitz"
                }
                button {
                    id = "logout-btn"
                    attributes["class"] = "px-4 py-2 text-red-400 hover:text-red-300"
                    +"Logout"
                }
            }

            // Main Content
            div {
                attributes["class"] = "flex-1 grid dashboard-grid grid-cols-1 md:grid-cols-4 gap-4 p-4 overflow-hidden"

                // Left sidebar - Chat history
                div {
                    attributes["class"] = "history-column bg-slate-800 rounded-xl p-4 flex flex-col overflow-hidden"
                    div {
                        attributes["class"] = "flex justify-between items-center mb-4"
                        button {
                            id = "new-chat-btn"
                            attributes["class"] = "px-4 py-2 bg-cyan-600 text-white rounded-lg hover:bg-cyan-500"
                            +"New Chat"
                        }
                        button {
                            id = "clear-chats-btn"
                            attributes["class"] = "px-4 py-2 text-red-400 hover:text-red-300"
                            +"Clear All"
                        }
                    }
                    div {
                        id = "chat-history"
                        attributes["class"] = "flex-1 overflow-y-auto space-y-2"
                    }
                }

                // Main chat area
                div {
                    attributes["class"] = "chat-column bg-slate-800 rounded-xl p-4 flex flex-col md:col-span-2 overflow-hidden"
                    div {
                        id = "chat-messages"
                        attributes["class"] = "flex-1 overflow-y-auto space-y-4 mb-4"
                    }
                    div {
                        attributes["class"] = "relative"
                        textArea {
                            id = "message-input"
                            attributes["class"] = "w-full p-4 bg-slate-700 rounded-lg focus:ring-2 focus:ring-cyan-500 pr-16 resize-none"
                            attributes["placeholder"] = "Ask Kotlin-Blitz..."
                            attributes["rows"] = "3"
                        }
                        button {
                            id = "send-btn"
                            attributes["class"] = "absolute right-4 bottom-4 p-2 bg-cyan-600 text-white rounded-lg hover:bg-cyan-500"
                            +"Send"
                        }
                    }
                }

                // Right sidebar - Model selection
                div {
                    attributes["class"] = "model-column bg-slate-800 rounded-xl p-4 flex flex-col overflow-hidden"
                    h2 {
                        attributes["class"] = "text-xl font-bold text-cyan-400 mb-4"
                        +"AI Models"
                    }
                    div {
                        id = "model-list"
                        attributes["class"] = "flex-1 overflow-y-auto grid gap-3"
                    }
                }
            }
        }

        script {
            unsafe {
                +"""
        // App state
        let currentUser = null;
        let ws = null;
        let selectedModel = 'llama3-70b-8192';
        
        // Initialize app
        document.addEventListener('DOMContentLoaded', function() {
            // Hide loading screen after 1 second
            setTimeout(function() {
                document.getElementById('loading-screen').classList.add('hidden');
                checkAuthState();
            }, 1000);
            
            // Setup event listeners
            setupEventListeners();
        });
        
        function setupEventListeners() {
            // Toggle password visibility
            document.getElementById('toggle-login-password').addEventListener('click', function(e) {
                e.preventDefault();
                togglePasswordVisibility('login-password', this);
            });
            
            document.getElementById('toggle-signup-password').addEventListener('click', function(e) {
                e.preventDefault();
                togglePasswordVisibility('signup-password', this);
            });
            
            // Send message
            document.getElementById('send-btn').addEventListener('click', sendMessage);
            document.getElementById('message-input').addEventListener('keypress', function(e) {
                if (e.key === 'Enter' && !e.shiftKey) {
                    e.preventDefault();
                    sendMessage();
                }
            });
            
            // New chat
            document.getElementById('new-chat-btn').addEventListener('click', function() {
                document.getElementById('chat-messages').innerHTML = '';
            });
            
            // Clear chats
            document.getElementById('clear-chats-btn').addEventListener('click', clearAllChats);
            
            // Logout
            document.getElementById('logout-btn').addEventListener('click', logout);
        }
        
        function togglePasswordVisibility(fieldId, toggleElement) {
            const field = document.getElementById(fieldId);
            if (field.type === 'password') {
                field.type = 'text';
                toggleElement.textContent = '🙈';
            } else {
                field.type = 'password';
                toggleElement.textContent = '👁️';
            }
        }
        
        function checkAuthState() {
            const token = localStorage.getItem('authToken');
            if (token) {
                validateToken(token);
            } else {
                showAuth();
            }
        }
        
        async function validateToken(token) {
            try {
                const response = await fetch('/user', {
                    headers: {
                        'Authorization': `Bearer \${'$'}{token}`
                    }
                });
                
                if (response.ok) {
                    currentUser = await response.json();
                    showDashboard();
                } else {
                    localStorage.removeItem('authToken');
                    showAuth();
                }
            } catch (error) {
                console.error('Token validation failed:', error);
                showAuth();
            }
        }
        
        function showAuth() {
            document.getElementById('auth-container').classList.add('active');
            document.getElementById('dashboard').classList.remove('active');
            switchTab('login');
        }
        
        function showDashboard() {
            document.getElementById('auth-container').classList.remove('active');
            document.getElementById('dashboard').classList.add('active');
            
            initWebSocket();
            loadModels();
            loadChatHistory();
        }
        
        function switchTab(tab) {
            if (tab === 'login') {
                document.getElementById('login-tab').classList.add('bg-slate-800', 'text-cyan-400');
                document.getElementById('login-tab').classList.remove('bg-slate-700', 'text-slate-400');
                document.getElementById('signup-tab').classList.add('bg-slate-700', 'text-slate-400');
                document.getElementById('signup-tab').classList.remove('bg-slate-800', 'text-cyan-400');
                document.getElementById('login-form-container').classList.remove('hidden');
                document.getElementById('signup-form-container').classList.add('hidden');
            } else {
                document.getElementById('signup-tab').classList.add('bg-slate-800', 'text-cyan-400');
                document.getElementById('signup-tab').classList.remove('bg-slate-700', 'text-slate-400');
                document.getElementById('login-tab').classList.add('bg-slate-700', 'text-slate-400');
                document.getElementById('login-tab').classList.remove('bg-slate-800', 'text-cyan-400');
                document.getElementById('signup-form-container').classList.remove('hidden');
                document.getElementById('login-form-container').classList.add('hidden');
            }
            document.getElementById('auth-response').textContent = '';
        }
        
        async function handleAuth(action) {
            const responseEl = document.getElementById('auth-response');
            responseEl.textContent = '';
            
            try {
                let url, data;
                
                if (action === 'login') {
                    url = '/user/sign-in';
                    data = {
                        email: document.getElementById('login-email').value,
                        password: document.getElementById('login-password').value
                    };
                } else {
                    url = '/user/sign-up';
                    data = {
                        firstName: document.getElementById('signup-firstName').value,
                        lastName: document.getElementById('signup-lastName').value,
                        email: document.getElementById('signup-email').value,
                        password: document.getElementById('signup-password').value
                    };
                }
                
                const response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                });
                
                const result = await response.json();
                
                if (response.ok) {
                    localStorage.setItem('authToken', result.token);
                    currentUser = result.user;
                    showDashboard();
                } else {
                    responseEl.textContent = result.message || 'Authentication failed';
                }
            } catch (error) {
                console.error('Auth error:', error);
                responseEl.textContent = 'An error occurred during authentication';
            }
        }
        
        function initWebSocket() {
            const token = localStorage.getItem('authToken');
            if (!token) return;
            
            if (ws) {
                ws.close();
            }
            
            ws = new WebSocket(`ws://\${'$'}{window.location.host}/chat?token=\${'$'}{token}`);
            
            ws.onopen = function() {
                console.log('WebSocket connected');
            };
            
            ws.onmessage = function(event) {
                const data = JSON.parse(event.data);
                appendMessage(data);
            };
            
            ws.onclose = function() {
                console.log('WebSocket disconnected');
                // Attempt to reconnect after 5 seconds
                setTimeout(initWebSocket, 5000);
            };
            
            ws.onerror = function(error) {
                console.error('WebSocket error:', error);
            };
        }
        
        function loadModels() {
            const models = [
                { id: 'llama3-70b-8192', name: 'Llama 3 70B' },
                { id: 'qwen-qwq-32b', name: 'Qwen 32B' },
                { id: 'deepseek-r1-distill-llama-70b-specdec', name: 'DeepSeek 70B' },
                { id: 'gemma2-9b-it', name: 'Gemma 2 9B' }
            ];
            
            const container = document.getElementById('model-list');
            container.innerHTML = '';
            
            models.forEach(model => {
                const btn = document.createElement('button');
                btn.className = 'p-3 bg-slate-700 rounded-lg hover:bg-slate-600 text-left';
                if (model.id === selectedModel) {
                    btn.classList.add('border-2', 'border-cyan-400');
                }
                btn.innerHTML = `
                    <div class="font-medium">\${'$'}{model.name}</div>
                    <div class="text-xs text-slate-400">\${'$'}{model.id}</div>
                `;
                btn.onclick = function() {
                    selectedModel = model.id;
                    document.querySelectorAll('#model-list button').forEach(b => 
                        b.classList.remove('border-2', 'border-cyan-400')
                    );
                    btn.classList.add('border-2', 'border-cyan-400');
                    if (ws) {
                        ws.send(JSON.stringify({ 
                            action: 'change_model',
                            model: selectedModel 
                        }));
                    }
                };
                container.appendChild(btn);
            });
        }
        
        async function loadChatHistory() {
            try {
                const token = localStorage.getItem('authToken');
                if (!token) return;
                
                const response = await fetch('/chat', {
                    headers: {
                        'Authorization': `Bearer \${'$'}{token}`
                    }
                });
                
                if (response.ok) {
                    const chats = await response.json();
                    const container = document.getElementById('chat-history');
                    container.innerHTML = '';
                    
                    chats.forEach(chat => {
                        const chatEl = document.createElement('div');
                        chatEl.className = 'p-3 bg-slate-700 rounded-lg cursor-pointer hover:bg-slate-600';
                        chatEl.innerHTML = `
                            <div class="font-medium truncate">\${'$'}{chat.title || 'New Chat'}</div>
                            <div class="text-xs text-slate-400 truncate">\${'$'}{new Date(chat.timestamp).toLocaleString()}</div>
                        `;
                        chatEl.onclick = function() {
                            loadChat(chat.id);
                        };
                        container.appendChild(chatEl);
                    });
                }
            } catch (error) {
                console.error('Failed to load chat history:', error);
            }
        }
        
        async function loadChat(chatId) {
            try {
                const token = localStorage.getItem('authToken');
                if (!token) return;
                
                const response = await fetch(`/chat/\${'$'}{chatId}`, {
                    headers: {
                        'Authorization': `Bearer \${'$'}{token}`
                    }
                });
                
                if (response.ok) {
                    const chat = await response.json();
                    const messagesContainer = document.getElementById('chat-messages');
                    messagesContainer.innerHTML = '';
                    
                    chat.messages.forEach(message => {
                        appendMessage(message);
                    });
                }
            } catch (error) {
                console.error('Failed to load chat:', error);
            }
        }
        
        async function clearAllChats() {
            try {
                const token = localStorage.getItem('authToken');
                if (!token) return;
                
                const response = await fetch('/chat', {
                    method: 'DELETE',
                    headers: {
                        'Authorization': `Bearer \${'$'}{token}`,
                        'Content-Type': 'application/json'
                    }
                });
                
                if (response.ok) {
                    document.getElementById('chat-history').innerHTML = '';
                    document.getElementById('chat-messages').innerHTML = '';
                }
            } catch (error) {
                console.error('Failed to clear chats:', error);
            }
        }
        
        function appendMessage(data) {
            const chatDiv = document.getElementById('chat-messages');
            const messageDiv = document.createElement('div');
            
            if (data.data?.status === 'loading') {
                messageDiv.className = 'typing-indicator flex gap-2 p-4';
                messageDiv.innerHTML = `
                    <div class="w-2 h-2 bg-cyan-400 rounded-full"></div>
                    <div class="w-2 h-2 bg-cyan-400 rounded-full"></div>
                    <div class="w-2 h-2 bg-cyan-400 rounded-full"></div>
                `;
            } else if (data.message) {
                messageDiv.className = 'bg-slate-700 p-4 rounded-lg';
                messageDiv.innerHTML = `
                    <div class="font-medium text-cyan-400">\${'$'}{data.sender || 'AI'}</div>
                    <div class="mt-1">\${'$'}{data.message}</div>
                `;
            }
            
            chatDiv.appendChild(messageDiv);
            chatDiv.scrollTop = chatDiv.scrollHeight;
        }
        
        function sendMessage() {
            const input = document.getElementById('message-input');
            const message = input.value.trim();
            
            if (message && ws) {
                // Add user message to chat
                appendMessage({
                    sender: 'You',
                    message: message
                });
                
                // Send to server
                ws.send(JSON.stringify({
                    message: message,
                    model: selectedModel
                }));
                
                input.value = '';
            }
        }
        
        function logout() {
            if (ws) {
                ws.close();
            }
            localStorage.removeItem('authToken');
            currentUser = null;
            showAuth();
        }
        """.trimIndent()
            }
        }

    }
}