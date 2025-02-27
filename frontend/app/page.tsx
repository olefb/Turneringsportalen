import React from "react";
import styles from "./page.module.css";
import Image from "next/image";
import { Button, Box, Container, Flex, Grid, Section, Separator } from "@radix-ui/themes";

export default function Page() {
	return (<>
		<Container size="4" style={{ minHeight: '100vh' }}>
      <Flex 
        direction="column" 
        style={{ 
          minHeight: '100vh', // This will make the container take up the full height of the screen
          gap: '2rem',
          padding: '2rem'
        }}
      >
        {/* Hero Section */}
        <Flex 
          align="center" 
          justify="center" 
          gap="9" // This will add space between the image and the content
          style={{ flex: 1 }} // This will make the hero section take up the remaining space
        >
          {/* Image Box */}
          <Box>
            <Image
              src="/turneringsportalen_logo.png"
              alt="turneringsportalen logo"
              width={400}
              height={400}
              style={{ objectFit: 'contain' }}
            />
          </Box>

          {/* Content Box */}
          <Flex direction="column" gap="4">
            <Box>
              <h1 style={{ fontSize: '2.5rem', marginBottom: '1rem' }}>Turneringsportalen</h1>
              <p><em>from idea to fully fledged tournament in no time</em></p>
            </Box>

            <Separator size="4"/>
            
            <Flex direction="column" gap="3">
              <p>A tournament organizer? Create your account now.</p>
              <Button size="3">Sign up</Button>
              <p>Already a user?</p>
              <Button variant="outline" size="3">Log in</Button>
							<p>Just want to set up a quick tournament?</p>
              <Button variant="outline" size="2">Click here</Button>
            </Flex>
          </Flex>
        </Flex>

        {/* Scroll Indicator */}
        <Box style={{ textAlign: 'center' }}>
          <Image
            src="/arrow-down.svg"
            alt="Scroll down"
            width={40}
            height={40}
						style={{ 
							filter: 'invert(1)', // This will make black become white
							opacity: 0.8 // Optional: adjust opacity for better visibility
						}}
          />
        </Box>
				<Section style={{ textAlign: 'center', padding: '2rem' }}>
					<h2>How it works</h2>
					<Grid columns="1fr 1fr 1fr" gap="4" style={{ marginTop: '2rem' }}>
						<Box>
							<h3>1. Create a tournament</h3>
					</Box>
					<Box>
						<h3>2. Invite participants</h3>
					</Box>
					<Box>
						<h3>3. Start the tournament</h3>
					</Box>
				</Grid>
			</Section>

        {/* Footer */}
				{/* TODO: Add links */}
        <Section style={{ textAlign: 'center', padding: '2rem', fontSize: '0.8rem', color: 'gray'}}>
  				<Flex align="center" justify="center" gap="4">
						<p>About Us</p>
						<Separator size="4" orientation="vertical"/>
						<p>Contact</p>
						<Separator size="4" orientation="vertical" />
						<p>Terms of Service</p>
						<Separator size="4" orientation="vertical" />
						<p>Privacy Policy</p>
						<Separator size="4" orientation="vertical" />
						<p>© 2025 Turneringsportalen</p>
  				</Flex>
				</Section>
      </Flex>
    </Container>
	</>);
}