import Link from 'next/link';


export default function Nav() {
  return (
    <nav>
      <Link href="/">TurneringsApp</Link>
      <Link href="/test">Test</Link>
      <Link href="/tournament">Tournament</Link>
      <Link href="/tournaments/create">Create tournament</Link>
    </nav>
  );
}

