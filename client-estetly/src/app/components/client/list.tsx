"use client";

import {useEffect, useState, useCallback} from 'react';
import {Client} from "@/app/api/client/model";
import {useDebounce} from "@/app/hooks/useDebounce";

export default function List() {
    const [searchTerm, setSearchTerm] = useState('');
    const [clients, setClients] = useState<Client[]>([]);
    const [loading, setLoading] = useState(false);

    // Debounce the search term to avoid too many API calls
    const debouncedSearchTerm = useDebounce(searchTerm, 500); // 500ms delay

    // Fetch clients function
    const fetchClients = useCallback(async (searchQuery: string = '') => {
        setLoading(true);
        try {
            const endpoint = searchQuery.trim()
                ? `/api/clients/search?name=${encodeURIComponent(searchQuery)}`
                : '/api/clients/getAll';

            const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}${endpoint}`);
            const data = await response.json();
            setClients(data);
        } catch (error) {
            console.error('Error fetching clients:', error);
        } finally {
            setLoading(false);
        }
    }, []);

    // Initial load and when debounced search term changes
    useEffect(() => {
        fetchClients(debouncedSearchTerm);
    }, [debouncedSearchTerm, fetchClients]);

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-2xl font-bold mb-4">Recherche de Clients</h1>

            <div className="flex mb-6">
                <input
                    type="text"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    placeholder="Rechercher par nom ou prénom"
                    className="flex-grow p-2 border border-gray-300 rounded-l"
                />
                <div className="flex items-center px-4 bg-gray-100 border-t border-b border-r border-gray-300">
                    {loading ? (
                        <span className="text-gray-500">Chargement...</span>
                    ) : (
                        <span className="text-gray-500">
                            {clients.length} résultats
                        </span>
                    )}
                </div>
            </div>

            {/* Results table remains the same */}
            {clients.length > 0 ? (
                <div className="overflow-x-auto">
                    <table className="min-w-full bg-white border border-gray-200">
                        <thead>
                        <tr className="bg-gray-100">
                            <th className="py-2 px-4 border-b">ID</th>
                            <th className="py-2 px-4 border-b">Nom</th>
                            <th className="py-2 px-4 border-b">Prénom</th>
                            <th className="py-2 px-4 border-b">Email</th>
                            <th className="py-2 px-4 border-b">Téléphone</th>
                        </tr>
                        </thead>
                        <tbody>
                        {clients.map((client) => (
                            <tr key={client.id} className="hover:bg-gray-50">
                                <td className="py-2 px-4 border-b">{client.id}</td>
                                <td className="py-2 px-4 border-b">{client.lastName}</td>
                                <td className="py-2 px-4 border-b">{client.firstName}</td>
                                <td className="py-2 px-4 border-b">{client.email}</td>
                                <td className="py-2 px-4 border-b">{client.phone}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            ) : (
                <p className="text-gray-500">
                    {loading ? 'Chargement...' : 'Aucun résultat à afficher'}
                </p>
            )}
        </div>
    );
}