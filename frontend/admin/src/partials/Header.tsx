import React from 'react';

import UserMenu from '../components/DropdownProfile';
import ThemeToggle from '../components/ThemeToggle';

interface HeaderProps {
  sidebarOpen: boolean;
  setSidebarOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

function Header({ sidebarOpen, setSidebarOpen }: HeaderProps) {
  return (
    <header className="sticky top-0 before:absolute before:inset-0 before:backdrop-blur-md max-lg:before:bg-white/90 dark:max-lg:before:bg-gray-800/90 before:-z-10 z-30 max-lg:shadow-xs lg:before:bg-gray-100/90 dark:lg:before:bg-gray-900/90">
      <div className="px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16 lg:border-b border-gray-200 dark:border-gray-700/60">
          <div className="flex min-w-0 items-center gap-3">
            <button
              className="shrink-0 text-gray-500 hover:text-gray-600 dark:hover:text-gray-400 lg:hidden"
              aria-controls="sidebar"
              aria-expanded={sidebarOpen}
              onClick={(e) => { e.stopPropagation(); setSidebarOpen(!sidebarOpen); }}
            >
              <span className="sr-only">Open sidebar</span>
              <svg className="w-6 h-6 fill-current" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <rect x="4" y="5" width="16" height="2" />
                <rect x="4" y="11" width="16" height="2" />
                <rect x="4" y="17" width="16" height="2" />
              </svg>
            </button>
          </div>

          <div className="flex shrink-0 items-center gap-1.5 sm:gap-3">
            <ThemeToggle />
            <hr className="hidden sm:block w-px h-6 bg-gray-200 dark:bg-gray-700/60 border-none" />
            <UserMenu align="right" />
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
