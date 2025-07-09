import {NextResponse} from 'next/server';

export async function GET(request: Request) {
    const {searchParams} = new URL(request.url);
    const name = searchParams.get('name');

    if (!name) {
        return NextResponse.json(
            {error: 'Name parameter is required'},
            {status: 400}
        );
    }

    try {
        const backendUrl = `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/clients/search?name=${encodeURIComponent(name)}`;
        const response = await fetch(backendUrl);

        if (!response.ok) {
            throw new Error('Backend request failed');
        }

        const data = await response.json();
        return NextResponse.json(data);
    } catch (error) {
        const message = error instanceof Error
            ? error.message
            : 'Unknown error occurred';
        return NextResponse.json(
            { error: 'Failed to fetch clients: ' + message },
            { status: 500 }
        );
    }
}