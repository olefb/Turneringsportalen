import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "../styles/globals.css";
import { Theme } from "@radix-ui/themes";
import { ThemeProvider}  from "next-themes";
import React from "react";


import Footer from "@/components/footer";
import NavigationMenu from "@/components/navigation-menu";



const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Turneringsportalen",
  description: "Generated by create next app",
};

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {


  return (
    <html lang="en" suppressHydrationWarning>
      <head/>
      <body>
        <ThemeProvider 
          attribute="class"
          defaultTheme="system"
          enableSystem
          disableTransitionOnChange
        >
          <Theme
            accentColor="sky"
            grayColor="gray"
            panelBackground="solid"
            scaling="100%"
            radius="full"
          >
            <NavigationMenu/>
            {children}
            <Footer/>
          </Theme>
        </ThemeProvider>
      </body>
    </html>
  );
}
