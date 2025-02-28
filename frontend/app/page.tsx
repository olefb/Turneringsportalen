import React from "react";
import styles from "@/styles/page.module.css";
import Image from "next/image";
import { Button, Box, Container, Flex, Grid, Section, Separator } from "@radix-ui/themes";
import { FaceIcon, ImageIcon, SunIcon } from "@radix-ui/react-icons"

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
          <svg width="50" height="50" viewBox="0 0 15 15" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M7.5 2C7.77614 2 8 2.22386 8 2.5L8 11.2929L11.1464 8.14645C11.3417 7.95118 11.6583 7.95118 11.8536 8.14645C12.0488 8.34171 12.0488 8.65829 11.8536 8.85355L7.85355 12.8536C7.75979 12.9473 7.63261 13 7.5 13C7.36739 13 7.24021 12.9473 7.14645 12.8536L3.14645 8.85355C2.95118 8.65829 2.95118 8.34171 3.14645 8.14645C3.34171 7.95118 3.65829 7.95118 3.85355 8.14645L7 11.2929L7 2.5C7 2.22386 7.22386 2 7.5 2Z" fill="currentColor" fillRule="evenodd" clipRule="evenodd"></path></svg>
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
      </Flex>
    </Container>
	</>);
}